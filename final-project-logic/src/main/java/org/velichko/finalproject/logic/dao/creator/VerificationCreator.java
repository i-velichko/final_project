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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VerificationCreator {
    private static Logger logger = LogManager.getLogger();

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
            verification.setApplicationDate(parseToLocalDateTime(resultSet.getString("application_date")));
            verification.setTrainerScore(resultSet.getDouble("trainer_score"));
            verification.setTrainerCharacteristic((resultSet.getString("characteristic")));
            verification.setTrainerVerificationDate(parseToLocalDateTime(resultSet.getString("trainer_verification_date")));
            verification.setExaminerVerificationDate(parseToLocalDateTime(resultSet.getString("examiner_verification_date")));
            verification.setFinalStatus(FinalStatus.valueOf(resultSet.getString("final_status")));

        } catch (SQLException e) {
            logger.log(Level.ERROR, "Create verification error. " + e.getMessage());
            throw new DaoException("Create user error. " + e.getMessage());
        }
        return verification;
    }

    private LocalDateTime parseToLocalDateTime(String dateTime) throws SQLException {
        LocalDateTime parse = null;
        if (dateTime != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            parse = LocalDateTime.parse(dateTime, dtf);
        }
        return parse;
    }
}
