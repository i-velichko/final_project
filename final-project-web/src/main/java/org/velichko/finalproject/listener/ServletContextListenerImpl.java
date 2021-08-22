package org.velichko.finalproject.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.velichko.finalproject.logic.pool.ConnectionPool;

/**
 * @author Ivan Velichko
 *
 * The type Servlet Context Listener Impl.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    /**
     * When ServletContext is destroyed:
     * Close all connection with DB
     * Deregister all DriverManager drivers
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroyPool();
    }
}
