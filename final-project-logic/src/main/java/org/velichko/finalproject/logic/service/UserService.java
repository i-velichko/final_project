package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;

import java.util.List;

public interface UserService {
    boolean create (User user);
    List<User> readAll ();
    boolean updateUser (UserDaoImpl userDao);
    boolean delete (UserDaoImpl userDao);
}
