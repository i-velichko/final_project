package org.velichko.finalproject.logic.service.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public boolean create(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace(); //todo выкидывать свой сервис эксепшн
        }finally {
            transaction.end();
        }
        return false;
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            users = userDao.findAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace(); //todo выкидывать свой сервис эксепшн
        }finally {
            transaction.end();
        }
        return users;
    }

    @Override
    public boolean updateUser(UserDaoImpl userDao) {
        return false;
    }

    @Override
    public boolean delete(UserDaoImpl userDao) {
        return false;
    }
}
