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
            logger.log(Level.ERROR, "Error with find all Users .", e);
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
    public boolean isLoginUnique(String login) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.findUserByLogin(login);
            if (currentUser.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by login", e);
            throw new ServiceException("Error with find user by login", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isPresent;
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.findUserByEmail(email);
            if (currentUser.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by email", e);
            throw new ServiceException("Error with find user by login", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isPresent;
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
            logger.log(Level.ERROR, "Error with find user by login and password", e);
            throw new ServiceException("Error with find user by login and password", e);
        } finally {
            transaction.endSingleQuery();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserById(Long id) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> currentUser;
        User user = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        try {
            currentUser = userDao.findEntityById(id);
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by id", e);
            throw new ServiceException("Error with find user by ID " + id, e);
        }finally {
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
