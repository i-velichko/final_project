package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.creator.UserCreator;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.utill.PasswordHashGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private UserCreator userCreator = new UserCreator();
    private static final String FIND_ALL_USERS = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "JOIN user_statuses as us ON u.status_id = us.id";
    private static final String FIND_USER_BY_ID = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "JOIN user_statuses as us ON u.status_id = us.id" +
            " WHERE u.id = ?";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "LEFT JOIN user_statuses as us ON u.status_id = us.id" +//TODO: remove left
            " WHERE u.login = ? and u.password = ?";
    private static final String FIND_USER_BY_LOGIN = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "LEFT JOIN user_statuses as us ON u.status_id = us.id" +//TODO: remove left
            " WHERE u.login = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "JOIN user_statuses as us ON u.status_id = us.id" +
            " WHERE u.email = ?";
    private static final String ADD_NEW_USER = "INSERT INTO users" +
            " (first_name, last_name, login, email, role_id, status_id, password)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?"; //todo делать одну константу файнд BY и несколько маленьких
    private static final String CHANGE_GIT = "UPDATE users SET git = ? WHERE login = ?";


    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        if (login != null && password != null) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
                statement.setString(1, login);
                statement.setString(2, PasswordHashGenerator.encodePassword(password));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                throw new DaoException("Error with find User by login .", e);
            } finally {
                close(statement);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        User user = null;
        if (login != null) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                throw new DaoException("Error with find User by login .", e);
            } finally {
                close(statement);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        User user = null;
        if (email != null) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(FIND_USER_BY_EMAIL);
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                throw new DaoException("Error with find User by email .", e);
            } finally {
                close(statement);
            }
        }

        return Optional.ofNullable(user);
    }


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = userCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with find all Users .", e);
        } finally {
            close(statement);
        }
        return users;
    }

    @Override
    public User findEntityById(long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with find User by id .", e);
        } finally {
            close(statement);
        }

        return user;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unsupported");
    }

    public boolean createNewUser(User user, String password) throws DaoException {

        PreparedStatement statement = null;
        if (user != null && password != null){
            try {
                statement = connection.prepareStatement(ADD_NEW_USER);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                statement.setInt(5, user.getRole().getId());
                statement.setInt(6, user.getStatus().getId());
                statement.setString(7, PasswordHashGenerator.encodePassword(password));
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new DaoException("Error with add new User. ", e);
            } finally {
                close(statement);
            }
        }
       return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public void updateUserGitLink(String login, String gitLink) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_GIT);
            statement.setString(1, gitLink);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing git link. ", e);
        } finally {
            close(statement);
        }
    }

    public void changePassword(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing password. ", e);
        } finally {
            close(statement);
        }
    }
}
