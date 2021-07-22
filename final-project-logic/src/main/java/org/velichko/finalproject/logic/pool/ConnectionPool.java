package org.velichko.finalproject.logic.pool;

import org.velichko.finalproject.logic.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static final long DELAY_UNTIL_CONNECTIONS_NUMBER_CHECK = 1;
    private static final long PERIOD_BETWEEN_CONNECTIONS_NUMBER_CHECK = 1;
    private static int DEFAULT_POOL_SIZE;
    private static AtomicBoolean isCreatedInstance = new AtomicBoolean(false);
    private final URL PROPERTIES_PATH = getClass().getClassLoader().getResource("connection.properties");
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> givenAwayConnections;
    private ReentrantLock connectionPoolLock;
    private AtomicBoolean connectionsNumberCheck = new AtomicBoolean(false);
    private Properties properties;


    private ConnectionPool() {
        try {

            if (PROPERTIES_PATH != null) {
                properties = PropertyLoader.loadPropertiesData(PROPERTIES_PATH);
            }
            DEFAULT_POOL_SIZE = Integer.parseInt(properties.getProperty("poolSize"));
            String url = properties.getProperty("URL");
            Class.forName(properties.getProperty("driverClassName"));
            freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
            givenAwayConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.FATAL, "Connection pool was not created: " + e.getMessage());
            throw new RuntimeException("Connection pool was not created " + e.getMessage());
        }
        Timer timer = new Timer();
        timer.schedule(new TimerConnectionPoolCheck()
                , TimeUnit.HOURS.toMillis(DELAY_UNTIL_CONNECTIONS_NUMBER_CHECK)
                , TimeUnit.HOURS.toMillis(PERIOD_BETWEEN_CONNECTIONS_NUMBER_CHECK));
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionPool HOLDER_INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            if (connectionsNumberCheck.get()) {
                try {
                    connectionPoolLock.lock();
                } finally {
                    connectionPoolLock.unlock();
                }
            }
            connection = freeConnections.take();
            givenAwayConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Can not getting connection ", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            givenAwayConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Cannot put connection", e);
                Thread.currentThread().interrupt();
            }
            return true;
        } else {
            logger.error("Attention. Attempt to transfer to the Connection Pool rogue connection.");
        }
        return false;
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException | SQLException e) {
                logger.log(Level.ERROR, "Error with destroying connection pool. ", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, " Deregister driver error" + e.getMessage());
            }
        });
    }


    public boolean poolSizeCheck() {
        boolean result = false;
        int currentConnectionsCount = 0;
        try {
            connectionPoolLock.lock();
            connectionsNumberCheck.set(true);
            TimeUnit.MICROSECONDS.sleep(100);
            currentConnectionsCount = givenAwayConnections.size() + freeConnections.size();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Cannot count connections");
            Thread.currentThread().interrupt();
        } finally {
            connectionsNumberCheck.set(false);
            connectionPoolLock.unlock();
        }

        if (currentConnectionsCount != DEFAULT_POOL_SIZE) {
            int missingConnections = DEFAULT_POOL_SIZE - currentConnectionsCount;
            String url = properties.getProperty("URL");
            int i = 0;
            while (i < missingConnections) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, properties);
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Can not creat connection" + e.getMessage());
                }
                freeConnections.offer(new ProxyConnection(connection));
                i++;
            }

            result = true;
        }
        return result;
    }

}
