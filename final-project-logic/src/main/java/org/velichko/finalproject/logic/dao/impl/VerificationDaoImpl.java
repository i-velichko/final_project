package org.velichko.finalproject.logic.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.dao.creator.VerificationCreator;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.pool.ConnectionPool;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * <p>
 * The type Verification dao.
 */
public class VerificationDaoImpl implements VerificationDao {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The constant DATE_PATTERN.
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final VerificationCreator verificationCreator = new VerificationCreator();

    private static final String FIND_ALL_VERIFICATIONS = "SELECT v.id, st.first_name as student_name," +
            " st.last_name as student_surname, v.title, tr.first_name as trainer_name, tr.last_name as trainer_surname," +
            " ex.first_name as examiner_name, ex.last_name as examiner_surname," +
            " v.application_date, v.trainer_verification_date, v.trainer_score, v.trainer_characteristic as characteristic, v.examiner_verification_date," +
            " vs.value as verification_status, fs.value as final_status FROM project_verification as v " +
            "LEFT JOIN verification_statuses as vs ON v.verification_status_id = vs.id " +
            "LEFT JOIN final_statuses as fs ON v.final_status_id = fs.id " +
            "LEFT JOIN users as st ON v.student_id = st.id " +
            "LEFT JOIN users as tr ON v.trainer_id = tr.id " +
            "LEFT JOIN users as ex ON v.examiner_id = ex.id";
    private static final String FIND_VERIFICATION_BY_ID = FIND_ALL_VERIFICATIONS + " WHERE v.id = ?";
    private static final String FIND_VERIFICATION_BY_STUDENT_ID = FIND_ALL_VERIFICATIONS + " WHERE v.student_id = ?";
    private static final String ADD_NEW_VERIFICATION = "INSERT INTO project_verification (title, student_id, trainer_id," +
            " verification_status_id, application_date) VALUES (?, ?, ?, ?, ?)";
    private static final String CHANGE_TRAINER_SCORE = "UPDATE project_verification SET trainer_score = ? WHERE id = ?";
    private static final String CHANGE_TRAINER_CHARACTERISTIC = "UPDATE project_verification SET trainer_characteristic = ? WHERE id = ?";
    private static final String CHANGE_VERIFICATION_STATUS = "UPDATE project_verification SET verification_status_id = ? WHERE id = ?";
    private static final String CHANGE_FINAL_VERIFICATION_STATUS = "UPDATE project_verification SET final_status_id = ? WHERE id = ?";
    private static final String CHANGE_TRAINER_VERIFICATION_DATE = "UPDATE project_verification SET trainer_verification_date = ? WHERE id = ?";
    private static final String CHANGE_EXAMINER_VERIFICATION_DATE = "UPDATE project_verification SET examiner_verification_date = ? WHERE id = ?";
    private static final String ORDER_BY = " ORDER BY v.id ";

    @Override
    public List<Verification> findAll() throws DaoException {
        return findListByQuery(FIND_ALL_VERIFICATIONS + ORDER_BY);
    }

    @Override
    public List<Verification> findByPage(int page) throws DaoException {
        return findListByQuery(buildPageableQuery(FIND_ALL_VERIFICATIONS + ORDER_BY, page));
    }

    private List<Verification> findListByQuery(String query) throws DaoException {
        List<Verification> verifications = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Verification verification = verificationCreator.createVerification(resultSet);
                verifications.add(verification);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with find all Verifications .", e);
            throw new DaoException("Error with find all Verifications .", e);
        }
        return verifications;
    }

    @Override
    public int getVerificationCount() throws DaoException {
        return rowCountByQuery(FIND_ALL_VERIFICATIONS);
    }


    @Override
    public boolean createNewVerification(Verification verification, String title) throws DaoException {
        if (verification != null && title != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(ADD_NEW_VERIFICATION)) {
                statement.setString(1, verification.getTitle());
                statement.setLong(2, verification.getStudent().getId());
                statement.setLong(3, verification.getTrainer().getId());
                statement.setInt(4, verification.getVerificationStatus().getId());
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);
                statement.setString(5, verification.getApplicationDate().format(format));
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error with create new Verification. ", e);
                throw new DaoException("Error with create new Verification. ", e);
            }
        }
        return false;
    }

    @Override
    public Optional<Verification> findEntityById(Long id) throws DaoException {
        Verification verification = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_VERIFICATION_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                verification = verificationCreator.createVerification(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with find Verification by id .", e);
            throw new DaoException("Error with find Verification by id .", e);
        }

        return Optional.ofNullable(verification);
    }

    @Override
    public Optional<Verification> findVerificationByStudentId(Long id) throws DaoException {
        Verification verification = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_VERIFICATION_BY_STUDENT_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                verification = verificationCreator.createVerification(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with find Verification by student id .", e);
            throw new DaoException("Error with find Verification by student id .", e);
        }
        return Optional.ofNullable(verification);
    }

    @Override
    public boolean changeTrainerScoreById(long id, double score) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_TRAINER_SCORE)) {
            statement.setDouble(1, score);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing trainer score. ", e);
            throw new DaoException("Error with changing trainer score. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeTrainerCharacteristicById(long id, String characteristic) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_TRAINER_CHARACTERISTIC)) {
            statement.setString(1, characteristic);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing trainer characteristic. ", e);
            throw new DaoException("Error with changing trainer characteristic. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeVerificationStatusById(long id, VerificationStatus finalStatus) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_VERIFICATION_STATUS)) {
            statement.setInt(1, finalStatus.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing verification status. ", e);
            throw new DaoException("Error with changing verification status. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_FINAL_VERIFICATION_STATUS)) {
            statement.setInt(1, finalStatus.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing final verification status. ", e);
            throw new DaoException("Error with changing final verification status. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeTrainerVerificationDateById(long id, String dateTime) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_TRAINER_VERIFICATION_DATE)) {
            statement.setString(1, dateTime);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing trainer verification date. ", e);
            throw new DaoException("Error with changing trainer verification date. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeExaminerVerificationDateById(long id, String dateTime) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_EXAMINER_VERIFICATION_DATE)) {
            statement.setString(1, dateTime);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error with changing examiner verification date. ", e);
            throw new DaoException("Error with changing examiner verification date. ", e);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean create(Verification entity) {
        return false;
    }

    @Override
    public boolean update(Verification entity) {
        return false;
    }

    @Override
    public void close(Statement statement) {
        VerificationDao.super.close(statement);
    }

    @Override
    public void close(Connection connection) {
        VerificationDao.super.close(connection);
    }
}
