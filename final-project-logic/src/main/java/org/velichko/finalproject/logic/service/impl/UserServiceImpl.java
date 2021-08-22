package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 *
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new User service.
     *
     * @param userDao the user dao
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> readAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
    }

    @Override
    public List<User> readByPage(int page) throws ServiceException {
        try {
            return userDao.findByPage(page);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
    }

    @Override
    public int getUserCount() throws ServiceException {
        try {
            return userDao.getUserCount();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with users count .", e);
            throw new ServiceException("Error with users count .", e);
        }
    }

    @Override
    public boolean createNewUser(User user, String password, String registrationKey) throws ServiceException {
        try {
            userDao.createNewUser(user, password, registrationKey);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with add new User. ", e);
            throw new ServiceException("Error with add new User. ", e);
        }
        return true;
    }

    @Override
    public boolean createNewUser(User user, String password) throws ServiceException {
        try {
            userDao.createNewUser(user, password);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with add new User. ", e);
            throw new ServiceException("Error with add new User. ", e);
        }
        return true;
    }

    @Override
    public boolean changeUserGit(String login, String gitLink) throws ServiceException {
        try {
            userDao.changeUserGitLinkByLogin(login, gitLink);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with changed user gitLink", e);
            throw new ServiceException("Impossible change gitLink for user", e);
        }
        return true;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        try {
            return userDao.findUserByLogin(login).isEmpty();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find user by login", e);
            throw new ServiceException("Error with find user by login", e);
        }
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        try {
            return userDao.findUserByEmail(email).isEmpty();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find user by email", e);
            throw new ServiceException("Error with find user by login", e);
        }
    }

    @Override
    public boolean isGitLinkUnique(String gitLink) throws ServiceException {
        try {
            return userDao.findUserByGitLink(gitLink).isEmpty();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find user project by git link", e);
            throw new ServiceException("Error with find user project by git link", e);
        }
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find user by login and password", e);
            throw new ServiceException("Error with find user by login and password", e);
        }
    }

    @Override
    public Optional<User> findUserById(Long id) throws ServiceException {
        try {
            return userDao.findEntityById(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with find user by id", e);
            throw new ServiceException("Error with find user by ID " + id, e);
        }
    }

    @Override
    public boolean changeUserRole(long id, UserRole role) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<User> optionalUser = userDao.findEntityById(id);
            if (optionalUser.isPresent()) {
                isChanged = userDao.changeUserRoleById(id, role);
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with changed user role", e);
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
                isChanged = userDao.changeUserStatusById(id, status);
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with changed user status", e);
            throw new ServiceException("Impossible change status for user", e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> getUserByRegistrationKey(String registrationKey) throws ServiceException {
        try {
            return userDao.findUserByRegistrationKey(registrationKey);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with get user by registration key", e);
            throw new ServiceException("Impossible get user", e);
        }
    }


    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Error. Impossible update user", e);
        }
    }

    @Override
    public boolean changeUserImage(String login, InputStream image) throws ServiceException {
        try {
            userDao.changeUserImage(login, image);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error with changed user image", e);
            throw new ServiceException("Impossible change image for user", e);
        }
        return true;
    }

}
