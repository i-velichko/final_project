package org.velichko.finalproject.logic.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.Entity;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 *
 * The interface Base dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDao<K, T extends Entity> {

    /**
     * The constant PAGE_SIZE.
     */
    int PAGE_SIZE = 5;

    /**
     * The constant LOGGER.
     */
    Logger LOGGER = LogManager.getLogger();

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Find by page list.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findByPage(int page) throws DaoException;

    /**
     * Find entity by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findEntityById(K id) throws DaoException;

    /**
     * Create Entity.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean create(T t) throws DaoException;

    /**
     * Update Entity.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(T t) throws DaoException;

    /**
     * Close Statement.
     *
     * @param statement the statement
     */
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            LOGGER.error("Closing statement error ", ex);
        }
    }

    /**
     * Close Connection.
     *
     * @param connection the connection
     */
    default void close(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Build pageable query string.
     *
     * @param mainQuery  the main query
     * @param pageNumber the page number
     * @return the string
     */
    default String buildPageableQuery(String mainQuery, int pageNumber) {
        int limit = PAGE_SIZE;
        int offset = (limit * pageNumber) - limit;
        StringBuilder queryBuilder = new StringBuilder(mainQuery);
        queryBuilder.append(" LIMIT ");
        if (offset > 0) {
            queryBuilder.append(offset).append(", ");
        }
        queryBuilder.append(limit);
        return queryBuilder.toString();
    }

    default int rowCountByQuery(String sourceQuery) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM (" + sourceQuery + ") as tbl" )
        ) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't count row count. ", e);
            throw new DaoException("Can't count row count.", e);
        }
        return result;
    }
}
