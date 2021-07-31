package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    public boolean createNewUser(User user, String password, String registrationKey) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    void updateUserGitLink(String login, String gitLink) throws DaoException;

    boolean changeUserRoleById(long id, UserRole role) throws DaoException;

    boolean changeUserStatusById(long id, UserStatus status) throws DaoException;
}
