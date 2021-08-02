package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.creator.UserCreator;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.utill.PasswordHashGenerator;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private UserCreator userCreator = new UserCreator();

    private static final String ADD_NEW_USER = "INSERT INTO users" +
            " (first_name, last_name, login, email, role_id, status_id, password, registration_key)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_USERS = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git, u.image" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "JOIN user_statuses as us ON u.status_id = us.id";
    private static final String FIND_USER_BY_ID = FIND_ALL_USERS + " WHERE u.id = ?";
    private static final String FIND_USER_BY_REGISTRATION_KEY = FIND_ALL_USERS + " WHERE registration_key = ?";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = FIND_ALL_USERS + " WHERE u.login = ? and u.password = ?";
    private static final String FIND_USER_BY_LOGIN = FIND_ALL_USERS + " WHERE u.login = ?";
    private static final String FIND_USER_BY_EMAIL = FIND_ALL_USERS + " WHERE u.email = ?";
    private static final String FIND_USER_BY_GIT_LINK = FIND_ALL_USERS + " WHERE u.git = ?";
    private static final String CHANGE_USER_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";
    private static final String CHANGE_USER_GIT = "UPDATE users SET git = ? WHERE login = ?";
    private static final String CHANGE_USER_IMAGE = "UPDATE users SET image = ? WHERE login = ?";
    private static final String CHANGE_USER_ROLE = "UPDATE users SET role_id = ? WHERE id = ?";
    private static final String CHANGE_USER_STATUS = "UPDATE users SET status_id = ? WHERE id = ?";

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
                while (resultSet.next()) {
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
                while (resultSet.next()) {
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
                while (resultSet.next()) {
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
    public Optional<User> findUserByGitLink(String gitLink) throws DaoException {
        User user = null;
        if (gitLink != null) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(FIND_USER_BY_GIT_LINK);
                statement.setString(1, gitLink);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                throw new DaoException("Error with find User by git link .", e);
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
    public Optional<User> findEntityById(long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with find User by id .", e);
        } finally {
            close(statement);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException {
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_REGISTRATION_KEY);
            statement.setString(1, registrationKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        } finally {
            close(statement);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean createNewUser(User user, String password, String registrationKey) throws DaoException {

        PreparedStatement statement = null;
        if (user != null && password != null) {
            try {
                statement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                statement.setInt(5, user.getRole().getId());
                statement.setInt(6, user.getStatus().getId());
                statement.setString(7, PasswordHashGenerator.encodePassword(password));
                statement.setString(8, registrationKey);
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
    public void changeUserGitLinkByLogin(String login, String gitLink) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_USER_GIT);
            statement.setString(1, gitLink);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing git link. ", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean changeUserRoleById(long id, UserRole role) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_USER_ROLE);
            statement.setInt(1, role.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error. Impossible get data from data base.", e);
        } finally {
            close(statement);
        }

        return rowsUpdate == 1;

    }

    @Override
    public boolean changeUserStatusById(long id, UserStatus status) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_USER_STATUS);
            statement.setInt(1, status.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error. Impossible get data from data base.", e);
        }
        return rowsUpdate == 1;
    }

    public void changePassword(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_USER_PASSWORD);
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing password. ", e);
        } finally {
            close(statement);
        }
    }

    public void changeUserImage(String login, InputStream image) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_USER_IMAGE);
            statement.setBlob(1, image);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing image. ", e);
        } finally {
            close(statement);
        }
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
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unsupported");
    }
}
