package org.velichko.finalproject.logic.dao.creator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Velichko
 *
 * The type User creator.
 */
public class UserCreator {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Create User.
     *
     * @param resultSet the result set
     * @return the user
     * @throws DaoException the dao exception
     */
    public User createUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setGitLink(resultSet.getString("git"));
            user.setStatus(UserStatus.valueOf(resultSet.getString("status")));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));
            user.setImage(resultSet.getBlob("image"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Create user error. " + e.getMessage());
            throw new DaoException("Create user error. " + e.getMessage());
        }
        return user;
    }

}
