package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class VerificationDaoImpl extends AbstractDao<Verification> implements VerificationDao {
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
    public List<Verification> findAll() {
        return null;
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
