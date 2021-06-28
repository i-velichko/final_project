package org.velichko.finalproject.logic.main;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class SomeService {
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin((AbstractDao<?>) userDao);
        try {
            users = (List<User>) ((AbstractDao<?>) userDao).findAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return users;
    }

    public User findUSerByID(long id) {
        User user = new User();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin((AbstractDao<?>) userDao);
        try {
            user = (User) ((AbstractDao<?>) userDao).findEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return user;
    }

    public User findUSerByLogin(String login) {
        User user = new User();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin((AbstractDao<?>) userDao);
        try {
            user = userDao.findUserByLogin(login);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return user;
    }

    public User findUSerByEmail(String email) {
        User user = new User();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin((AbstractDao<?>) userDao);
        try {
            user = userDao.findUserByEmail(email);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return user;
    }

    public boolean addUser(User user) { //todo castы убрать
        boolean result = false;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            result = userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return result;
    }
}
