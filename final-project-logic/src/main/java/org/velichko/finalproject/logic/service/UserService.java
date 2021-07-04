package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> readAll () throws ServiceException;
    boolean updateUser (UserDaoImpl userDao);
    boolean delete (UserDaoImpl userDao);
    boolean createNewUser (User user, String password) throws ServiceException;
}
