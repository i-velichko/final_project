package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.pool.ConnectionPool;
import org.velichko.finalproject.logic.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class EntityTransaction {
    private static Logger logger = LogManager.getLogger();
    private ProxyConnection connection;

    public EntityTransaction() {
    }

    public void beginSingleQuery(AbstractDao<?> dao) {
        if (connection == null) {
            connection = (ProxyConnection) ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    public void begin(AbstractDao<?>... daos) {
        if (connection == null) {
            connection = (ProxyConnection) ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not execute query " + e.getMessage());
        }
        for (AbstractDao<?> daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Can not change status for autocommit " + e.getMessage());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void endSingleQuery(AbstractDao<?> dao) {
        if (connection != null){
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with commit transaction " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with rollback transaction " + e.getMessage());
        }
    }

}
