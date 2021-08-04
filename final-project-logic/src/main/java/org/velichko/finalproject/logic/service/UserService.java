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

public interface UserService {
    List<User> readAll () throws ServiceException;
    boolean updateUser (UserDaoImpl userDao) throws ServiceException; //todo  реализовать или убрать
    boolean changeUserImage (String login, InputStream image) throws ServiceException;
    boolean createNewUser (User user, String password, String registrationKey) throws ServiceException;
    boolean changeUserGit (String login, String gitLink) throws ServiceException;
    boolean isLoginUnique(String login) throws ServiceException;
    boolean isEmailUnique(String email) throws ServiceException;
     boolean isGitLinkUnique(String gitLink) throws ServiceException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;
    Optional<User> findUserById(Long id) throws ServiceException;

    boolean changeUserRole(long id, UserRole role) throws ServiceException;
    boolean changeUserStatus(long id, UserStatus status) throws ServiceException;

    Optional<User> getUserByRegistrationKey(String confirmationKey) throws ServiceException;
}
