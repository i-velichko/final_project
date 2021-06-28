package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.Entity;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract T findEntityById(long id) throws DaoException;

    public abstract boolean delete(long id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean update(T entity);

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();//todo log
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }
}
