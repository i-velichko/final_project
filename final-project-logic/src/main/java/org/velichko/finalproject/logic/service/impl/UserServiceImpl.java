package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> readAll() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
        return users;
    }

    @Override
    public List<User> readByPage(int page) throws ServiceException {
        List<User> users;
        try {
            users = userDao.findByPage(page);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
        return users;
    }

    @Override
    public int getUserCount() throws ServiceException {
        try {
            return userDao.getUserCount();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with users count .", e);
            throw new ServiceException("Error with users count .", e);
        }
    }

    @Override
    public boolean createNewUser(User user, String password, String registrationKey) throws ServiceException {
        boolean result;
        try {
            userDao.createNewUser(user, password, registrationKey);
            result = true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with add new User. ", e);
            throw new ServiceException("Error with add new User. ", e);
        }
        return result;
    }

    @Override
    public boolean changeUserGit(String login, String gitLink) throws ServiceException {
        try {
            userDao.changeUserGitLinkByLogin(login, gitLink);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed user gitLink", e);
            throw new ServiceException("Impossible change gitLink for user", e);
        }
        return true;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.findUserByLogin(login);
            if (currentUser.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by login", e);
            throw new ServiceException("Error with find user by login", e);
        }
        return isPresent;
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.findUserByEmail(email);
            if (currentUser.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by email", e);
            throw new ServiceException("Error with find user by login", e);
        }
        return isPresent;
    }

    @Override
    public boolean isGitLinkUnique(String gitLink) throws ServiceException {
        boolean isPresent = false;
        try {
            Optional<User> optionalUser = userDao.findUserByGitLink(gitLink);
            if (optionalUser.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user project by git link", e);
            throw new ServiceException("Error with find user project by git link", e);
        }
        return isPresent;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        Optional<User> currentUser;
        User user = null;
        try {
            currentUser = userDao.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by login and password", e);
            throw new ServiceException("Error with find user by login and password", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserById(Long id) throws ServiceException {
        Optional<User> currentUser;
        User user = null;
        try {
            currentUser = userDao.findEntityById(id);
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user by id", e);
            throw new ServiceException("Error with find user by ID " + id, e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean changeUserRole(long id, UserRole role) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<User> optionalUser = userDao.findEntityById(id);
            if (optionalUser.isPresent()) {
                userDao.changeUserRoleById(id, role);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed user role", e);
            throw new ServiceException("Impossible change role for user", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeUserStatus(long id, UserStatus status) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<User> optionalUser = userDao.findEntityById(id);
            if (optionalUser.isPresent()) {
                userDao.changeUserStatusById(id, status);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed user status", e);
            throw new ServiceException("Impossible change status for user", e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> getUserByRegistrationKey(String registrationKey) throws ServiceException {
        Optional<User> optionalUser;
        User user;
        try {
            optionalUser = userDao.findUserByRegistrationKey(registrationKey);
            user = optionalUser.orElse(null);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with get user by registration key", e);
            throw new ServiceException("Impossible get user", e);
        }
        return Optional.ofNullable(user);
    }


    @Override
    public boolean updateUser(UserDaoImpl userDao) {
        return false;
    }

    @Override
    public boolean changeUserImage(String login, InputStream image) throws ServiceException {
        try {
            userDao.changeUserImage(login, image);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed user image", e);
            throw new ServiceException("Impossible change image for user", e);
        }
        return true;
    }

}
