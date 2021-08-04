package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.io.InputStream;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    boolean createNewUser(User user, String password, String registrationKey) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    void changeUserGitLinkByLogin(String login, String gitLink) throws DaoException;

    boolean changeUserRoleById(long id, UserRole role) throws DaoException;

    boolean changeUserStatusById(long id, UserStatus status) throws DaoException;

    Optional<User> findUserByGitLink(String gitLink) throws DaoException;

    void changePassword(String login, String password) throws DaoException;

    void changeUserImage(String login, InputStream image) throws DaoException;

    Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException;
}
