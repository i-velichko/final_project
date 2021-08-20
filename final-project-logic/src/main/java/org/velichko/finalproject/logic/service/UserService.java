package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

/**
 * author Ivan Velichko
 * <p>
 * The interface User service.
 *
 * @author Ivan Velichko The interface User service.
 */
public interface UserService {
    /**
     * Read all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> readAll () throws ServiceException;

    /**
     * Read by page list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> readByPage (int page) throws ServiceException;

    /**
     * Gets user count.
     *
     * @return the user count
     * @throws ServiceException the service exception
     */
    int getUserCount () throws ServiceException;

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUser (User user) throws ServiceException;

    /**
     * Change user image boolean.
     *
     * @param login the login
     * @param image the image
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserImage (String login, InputStream image) throws ServiceException;

    /**
     * Create new user boolean.
     *
     * @param user            the user
     * @param password        the password
     * @param registrationKey the registration key
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewUser (User user, String password, String registrationKey) throws ServiceException;

    /**
     * Create new user boolean.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     */
    boolean createNewUser(User user, String password) throws ServiceException;

    /**
     * Change user git boolean.
     *
     * @param login   the login
     * @param gitLink the git link
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserGit (String login, String gitLink) throws ServiceException;

    /**
     * Is login unique boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginUnique(String login) throws ServiceException;

    /**
     * Is email unique boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isEmailUnique(String email) throws ServiceException;

    /**
     * Is git link unique boolean.
     *
     * @param gitLink the git link
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isGitLinkUnique(String gitLink) throws ServiceException;

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Find user by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserById(Long id) throws ServiceException;

    /**
     * Change user role boolean.
     *
     * @param id   the id
     * @param role the role
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserRole(long id, UserRole role) throws ServiceException;

    /**
     * Change user status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserStatus(long id, UserStatus status) throws ServiceException;

    /**
     * Gets user by registration key.
     *
     * @param confirmationKey the confirmation key
     * @return the user by registration key
     * @throws ServiceException the service exception
     */
    Optional<User> getUserByRegistrationKey(String confirmationKey) throws ServiceException;

}
