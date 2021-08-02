package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.dao.creator.VerificationCreator;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.utill.PasswordHashGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VerificationDaoImpl extends AbstractDao<Verification> implements VerificationDao {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String FIND_ALL_VERIFICATIONS = "SELECT v.id, st.first_name as student_name," +
            " st.last_name as student_surname, v.title, tr.first_name as trainer_name, tr.last_name as trainer_surname," +
            " ex.first_name as examiner_name, ex.last_name as examiner_surname," +
            " v.application_date, v.trainer_verification_date, v.trainer_score, v.trainer_characteristic as characteristic, v.examiner_verification_date," +
            " vs.value as verification_status, fs.value as final_status FROM project_verification as v" +
            " LEFT JOIN verification_statuses as vs ON v.verification_status_id = vs.id " +
            "LEFT JOIN final_statuses as fs ON v.final_status_id = fs.id " +
            "LEFT JOIN users as st ON v.student_id = st.id " +
            "LEFT JOIN users as tr ON v.trainer_id = tr.id " +
            "LEFT JOIN users as ex ON v.examiner_id = ex.id";
    private static final String FIND_VERIFICATION_BY_ID = FIND_ALL_VERIFICATIONS + " WHERE v.id = ?";
    private static final String ADD_NEW_VERIFICATION = "INSERT INTO project_verification (title, student_id, trainer_id," +
            " verification_status_id, application_date) VALUES (?, ?, ?, ?, ?)";
    private static final String CHANGE_TRAINER_SCORE = "UPDATE project_verification SET trainer_score = ? WHERE id = ?";
    private static final String CHANGE_TRAINER_CHARACTERISTIC = "UPDATE project_verification SET trainer_characteristic = ? WHERE id = ?";
    private static final String CHANGE_VERIFICATION_STATUS = "UPDATE project_verification SET verification_status_id = ? WHERE id = ?";
    private static final String CHANGE_FINAL_VERIFICATION_STATUS = "UPDATE project_verification SET final_status_id = ? WHERE id = ?";
    private static final String CHANGE_TRAINER_VERIFICATION_DATE = "UPDATE project_verification SET trainer_verification_date = ? WHERE id = ?";
    private static final String CHANGE_EXAMINER_VERIFICATION_DATE = "UPDATE project_verification SET examiner_verification_date = ? WHERE id = ?";


    private VerificationCreator verificationCreator = new VerificationCreator();

    @Override
    public boolean createNewVerification(Verification verification, String title) throws DaoException {
        PreparedStatement statement = null;
        if (verification != null && title != null) {
            try {
                statement = connection.prepareStatement(ADD_NEW_VERIFICATION);
                statement.setString(1, verification.getTitle());
                statement.setLong(2, verification.getStudent().getId());
                statement.setLong(3, verification.getTrainer().getId());
                statement.setInt(4, verification.getVerificationStatus().getId());
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);
                statement.setString(5, verification.getApplicationDate().format(format));
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new DaoException("Error with create new Verification. ", e);
            } finally {
                close(statement);
            }
        }
        return false;
    }

    @Override
    public List<Verification> findAll() throws DaoException {
        List<Verification> verifications = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ALL_VERIFICATIONS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Verification verification = verificationCreator.createVerification(resultSet);
                verifications.add(verification);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with find all Verifications .", e);
        } finally {
            close(statement);
        }
        return verifications;
    }

    @Override
    public Optional<Verification> findEntityById(long id) throws DaoException {
        Verification verification = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_VERIFICATION_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                verification = verificationCreator.createVerification(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with find Verification by id .", e);
        } finally {
            close(statement);
        }

        return Optional.ofNullable(verification);
    }

    @Override
    public boolean changeTrainerScoreById(long id, double score) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_TRAINER_SCORE);
            statement.setDouble(1, score);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing trainer score. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeTrainerCharacteristicById(long id, String characteristic) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_TRAINER_CHARACTERISTIC);
            statement.setString(1, characteristic);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing trainer characteristic. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeVerificationStatusById(long id, VerificationStatus finalStatus) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_VERIFICATION_STATUS);
            statement.setInt(1, finalStatus.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing verification status. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_FINAL_VERIFICATION_STATUS);
            statement.setInt(1, finalStatus.getId());
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing final verification status. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeTrainerVerificationDateById(long id, String dateTime) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_TRAINER_VERIFICATION_DATE);
            statement.setString(1, dateTime);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing trainer verification date. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeExaminerVerificationDateById(long id, String dateTime) throws DaoException {
        PreparedStatement statement = null;
        int rowsUpdate;
        try {
            statement = connection.prepareStatement(CHANGE_EXAMINER_VERIFICATION_DATE);
            statement.setString(1, dateTime);
            statement.setLong(2, id);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing examiner verification date. ", e);
        } finally {
            close(statement);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Verification entity) {
        return false;
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
    public Verification findVerificationByVerificationStatusId(long id) {
        return null;
    }

    @Override
    public Verification findVerificationByFinalStatusId(long id) {
        return null;
    }

    @Override
    public Verification findVerificationByStudentId(long id) {
        return null;
    }

    @Override
    public List<Verification> findVerificationsByTrainerScore(long id) {
        return null;
    }

    @Override
    public Optional<Verification> findVerificationByGitLink(String gitLink) {
        return Optional.empty();
    }

}
