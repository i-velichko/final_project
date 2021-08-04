package org.velichko.finalproject.logic.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.Entity;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<K, T extends Entity> {
    Logger logger = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    Optional<T> findEntityById(K id) throws DaoException;

    boolean create(T t) throws DaoException;

    boolean update(T t);

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            logger.error("Closing statement error ", ex);
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
