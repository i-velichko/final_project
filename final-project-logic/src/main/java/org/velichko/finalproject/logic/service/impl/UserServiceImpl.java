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
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<User> readAll() throws ServiceException {
        List<User> users;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            users = userDao.findAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Error with find all Users .", e);
        } finally {
            transaction.end();
        }
        return users;
    }

    @Override
    public boolean createNewUser(User user, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        User newUser = new User();
        transaction.begin(userDao);
        boolean result;
        try {
            userDao.createNewUser(newUser, password);
            transaction.commit();
            result = true;
        } catch (DaoException e) {
            throw new ServiceException("Error with add new User. ", e);
        } finally {
            transaction.end();
        }
        return result;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> currentUser;
        User user = null;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            currentUser = userDao.findUserByLogin(login);
            transaction.commit();
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException("Error with find user by login", e);
        }finally {
            transaction.end();
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
