package org.velichko.finalproject.logic.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.creator.UserCreator;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.pool.ConnectionPool;
import org.velichko.finalproject.logic.util.PasswordHashGenerator;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger();
    private final UserCreator userCreator = new UserCreator();

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
    private static final String UPDATE_USER = "UPDATE users SET first_name=?, last_name=?,  git=?, email=? WHERE id= ?";
    private static final String ORDER_BY = " ORDER BY u.first_name ";

    @Override
    public List<User> findAll() throws DaoException {
        return findListByQuery(FIND_ALL_USERS + ORDER_BY);
    }

    @Override
    public List<User> findByPage(int page) throws DaoException {
        return findListByQuery(buildPageableQuery(FIND_ALL_USERS + ORDER_BY, page));
    }

    private List<User> findListByQuery(String query) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = userCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new DaoException("Error with find all Users .", e);
        }
        return users;
    }

    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with find User by id .", e);
            throw new DaoException("Error with find User by id .", e);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        if (login != null && password != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
                statement.setString(1, login);
                statement.setString(2, PasswordHashGenerator.encodePassword(password));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error with find User by login .", e);
                throw new DaoException("Error with find User by login .", e);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        User user = null;
        if (login != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error with find User by login .", e);
                throw new DaoException("Error with find User by login .", e);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        User user = null;
        if (email != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error with find User by email .", e);
                throw new DaoException("Error with find User by email .", e);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByGitLink(String gitLink) throws DaoException {
        User user = null;
        if (gitLink != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_GIT_LINK)) {
                statement.setString(1, gitLink);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = userCreator.createUser(resultSet);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error with find User by git link .", e);
                throw new DaoException("Error with find User by git link .", e);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_REGISTRATION_KEY)) {
            statement.setString(1, registrationKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error. Impossible get data from data base.", e);
            throw new DaoException("Error. Impossible get data from data base.", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public int getUserCount() throws DaoException {
        return rowCountByQuery(FIND_ALL_USERS);
    }

    @Override
    public boolean createNewUser(User user, String password, String registrationKey) throws DaoException {
        if (user != null && password != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
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
                logger.log(Level.ERROR, "Error with add new User. ", e);
                throw new DaoException("Error with add new User. ", e);
            }
        }
        return false;
    }

    @Override
    public void changeUserGitLinkByLogin(String login, String gitLink) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_GIT)) {
            statement.setString(1, gitLink);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with changing git link. ", e);
            throw new DaoException("Error with changing git link. ", e);
        }
    }

    @Override
    public boolean changeUserRoleById(long id, UserRole role) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_ROLE)) {
            statement.setInt(1, role.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with changing user role. ", e);
            throw new DaoException("Error with changing user role. ", e);
        }

        return rowsUpdate == 1;

    }

    @Override
    public boolean changeUserStatusById(long id, UserStatus status) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_STATUS)) {
            statement.setInt(1, status.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with changing user status. ", e);
            throw new DaoException("Error with changing user status. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public void changePassword(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with changing password. ", e);
            throw new DaoException("Error with changing password. ", e);
        }
    }

    @Override
    public void changeUserImage(String login, InputStream image) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_IMAGE)) {
            statement.setBlob(1, image);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with changing image. ", e);
            throw new DaoException("Error with changing image. ", e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getGitLink());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getId());
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with update user.", e);
        }
        return rowsUpdate == 1;
    }

    private int rowCountByQuery(String sourceQuery) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM (" + sourceQuery + ") as tbl")
        ) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't count row count. ", e);
            throw new DaoException("Can't count row count.", e);
        }
        return result;
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unsupported");
    }

    @Override
    public void close(Statement statement) {
        UserDao.super.close(statement);
    }

    @Override
    public void close(Connection connection) {
        UserDao.super.close(connection);
    }
}
