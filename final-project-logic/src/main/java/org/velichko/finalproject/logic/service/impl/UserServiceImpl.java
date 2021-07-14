package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<User> readAll() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users;
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e );
            throw new ServiceException("Error with find all Users .", e);
        } finally {
            transaction.endSingleQuery();
        }
        return users;
    }

    @Override
    public boolean createNewUser(User user, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        boolean result;
        try {
            userDao.createNewUser(user, password);
            result = true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with add new User. ", e);
            throw new ServiceException("Error with add new User. ", e);
        } finally {
            transaction.endSingleQuery();
        }
        return result;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> currentUser;
        User user = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        try {
            currentUser = userDao.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException("Error with find user by login", e);
        } finally {
            transaction.endSingleQuery();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean updateUser(UserDaoImpl userDao) {
        return false;
    }

    @Override
    public boolean delete(UserDaoImpl userDao) {
        return false;
    }


}
