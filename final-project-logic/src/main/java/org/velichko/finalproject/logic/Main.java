package org.velichko.finalproject.logic;

import org.apache.logging.log4j.Level;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.creator.UserCreator;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.velichko.finalproject.logic.dao.BaseDao.logger;

/**
 * @author Ivan Velichko
 *
 * The type Main.
 */
public class Main {
    private static final String FIND_ALL_USERS = "SELECT u.id, u.first_name, u.last_name, u.login, u.email, u.git, u.image" +
            ",r.value as role, us.value as status FROM users as u JOIN roles as r ON u.role_id = r.id " +
            "JOIN user_statuses as us ON u.status_id = us.id";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DaoException the dao exception
     */
    public static void main(String[] args) throws DaoException {

        UserCreator userCreator = new UserCreator();
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildQuery(2))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = userCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new DaoException("Error with find all Users .", e);
        }
        users.forEach(System.out::println);

    }

    /**
     * Build query string.
     *
     * @return the string
     */
    public static String buildQuery() {
        return buildQuery(1);
    }

    /**
     * Build query string.
     *
     * @param pageNumber the page number
     * @return the string
     */
    public static String buildQuery(int pageNumber) {
        int limit = 3;
        int offset = (limit * pageNumber) - limit;
        StringBuilder queryBuilder = new StringBuilder(FIND_ALL_USERS);
        queryBuilder.append(" LIMIT ");
        if (offset > 0) {
            queryBuilder.append(offset).append(", ");
        }
        queryBuilder.append(limit);
        return queryBuilder.toString();
    }


}
