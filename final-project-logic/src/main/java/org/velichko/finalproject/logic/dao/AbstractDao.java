package org.velichko.finalproject.logic.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.Entity;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> {
    private static final Logger logger = LogManager.getLogger();
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findEntityById(long id) throws DaoException;

    public abstract boolean delete(long id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean update(T entity);

    public void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARN, "Error with statement closing", e);
            throw new DaoException("Error with statement closing", e);
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException;
}
