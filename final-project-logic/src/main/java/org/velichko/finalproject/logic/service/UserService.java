package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> readAll () throws ServiceException;
    boolean updateUser (UserDaoImpl userDao) throws ServiceException; //todo  реализовать или убрать
    boolean delete (UserDaoImpl userDao) throws ServiceException; //todo  реализовать или убрать
    boolean createNewUser (User user, String password) throws ServiceException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;
}
