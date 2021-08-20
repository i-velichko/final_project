package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.io.InputStream;
import java.util.Optional;

/**
 * author Ivan Velichko
 * <p>
 * The interface User dao.
 *
 * @author Ivan Velichko The interface User dao.
 */
public interface UserDao extends BaseDao<Long, User> {
    /**
     * Gets user count.
     *
     * @return the user count
     * @throws DaoException the dao exception
     */
    int getUserCount() throws DaoException;

    /**
     * Create new user boolean.
     *
     * @param user            the user
     * @param password        the password
     * @param registrationKey the registration key
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createNewUser(User user, String password, String registrationKey) throws DaoException;

    /**
     * Create new user.
     *  @param user     the user
     * @param password the password
     * @return
     */
    boolean createNewUser(User user, String password) throws DaoException;

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Change user git link by login.
     *
     * @param login   the login
     * @param gitLink the git link
     * @throws DaoException the dao exception
     */
    void changeUserGitLinkByLogin(String login, String gitLink) throws DaoException;

    /**
     * Change user role by id boolean.
     *
     * @param id   the id
     * @param role the role
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeUserRoleById(long id, UserRole role) throws DaoException;

    /**
     * Change user status by id boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeUserStatusById(long id, UserStatus status) throws DaoException;

    /**
     * Find user by git link optional.
     *
     * @param gitLink the git link
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByGitLink(String gitLink) throws DaoException;

    /**
     * Change password.
     *
     * @param login    the login
     * @param password the password
     * @throws DaoException the dao exception
     */
    void changePassword(String login, String password) throws DaoException;

    /**
     * Change user image.
     *
     * @param login the login
     * @param image the image
     * @throws DaoException the dao exception
     */
    void changeUserImage(String login, InputStream image) throws DaoException;

    /**
     * Find user by registration key optional.
     *
     * @param registrationKey the registration key
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException;

}
