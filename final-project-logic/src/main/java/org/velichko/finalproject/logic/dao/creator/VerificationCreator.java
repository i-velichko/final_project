package org.velichko.finalproject.logic.dao.creator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.velichko.finalproject.logic.util.DateTimeUtil.timestampToLocalDateTime;

/**
 * @author Ivan Velichko
 * 
 * The type Verification creator.
 */
public class VerificationCreator {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Create verification.
     *
     * @param resultSet the result set
     * @return the verification
     * @throws DaoException the dao exception
     */
    public Verification createVerification(ResultSet resultSet) throws DaoException {
        Verification verification = new Verification();
        try {
            verification.setId(resultSet.getInt("id"));
            verification.setStudent(new User(
                    resultSet.getString("student_name"), resultSet.getString("student_surname")));
            verification.setTitle(resultSet.getString("title"));
            verification.setTrainer(new User(
                    resultSet.getString("trainer_name"), resultSet.getString("trainer_surname")));
            verification.setExaminer(new User(
                    resultSet.getString("examiner_name"), resultSet.getString("examiner_surname")));
            verification.setVerificationStatus(VerificationStatus.valueOf(resultSet.getString("verification_status")));
            verification.setApplicationDate(timestampToLocalDateTime(resultSet.getTimestamp("application_date")));
            verification.setTrainerScore(resultSet.getDouble("trainer_score"));
            verification.setTrainerCharacteristic((resultSet.getString("characteristic")));
            verification.setTrainerVerificationDate(timestampToLocalDateTime(resultSet.getTimestamp("trainer_verification_date")));
            verification.setExaminerVerificationDate(timestampToLocalDateTime(resultSet.getTimestamp("examiner_verification_date")));
            verification.setFinalStatus(FinalStatus.valueOf(resultSet.getString("final_status")));

            resultSet.getTimestamp("application_date").toLocalDateTime();

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Create verification error. ", e);
            throw new DaoException("Create user error. ", e);
        }
        return verification;
    }

}
