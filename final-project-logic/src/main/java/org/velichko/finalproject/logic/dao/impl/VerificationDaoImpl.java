package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.dao.creator.VerificationCreator;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VerificationDaoImpl extends AbstractDao<Verification> implements VerificationDao {
    private static final String FIND_ALL_VERIFICATIONS = "SELECT v.id, st.first_name as student_name," +
            " st.last_name as student_surname, tr.first_name as trainer_name, tr.last_name as trainer_surname," +
            " ex.first_name as examiner_name, ex.last_name as examiner_surname," +
            " v.application_date, v.trainer_verification_date, v.trainer_score, v.trainer_characteristic as characteristic, v.examiner_verification_date," +
            " vs.value as verification_status, fs.value as final_status FROM project_verification as v" +
            " LEFT JOIN verification_statuses as vs ON v.verification_status_id = vs.id " +
            "LEFT JOIN final_statuses as fs ON v.final_status_id = fs.id " +
            "LEFT JOIN users as st ON v.student_id = st.id " +
            "LEFT JOIN users as tr ON v.trainer_id = tr.id " +
            "LEFT JOIN users as ex ON v.examiner_id = ex.id";
    private VerificationCreator verificationCreator = new VerificationCreator();

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
    public Optional<Verification> findEntityById(long id) {
        return null;
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
    public void close(Statement statement) throws DaoException {
        super.close(statement);
    }
}
