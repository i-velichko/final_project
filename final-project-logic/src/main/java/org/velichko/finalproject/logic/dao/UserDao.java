package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    void updateUserGitLink(String login, String gitLink) throws DaoException;


}
