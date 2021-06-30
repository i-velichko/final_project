package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.DaoException;

public interface UserDao{

    User findUserByLogin(String login) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    void updateUserGitLink(String login, String gitLink) throws DaoException;


}
