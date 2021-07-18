package org.velichko.finalproject.logic.dao.impl;

import org.velichko.finalproject.logic.dao.AbstractDao;
import org.velichko.finalproject.logic.dao.QuestionDao;
import org.velichko.finalproject.logic.entity.Question;
import org.velichko.finalproject.logic.exception.DaoException;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {
    @Override
    public Question findQuestionByDifficultID(long id) {
        return null;
    }

    @Override
    public Question findQuestionByTypeID(long id) {
        return null;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public Optional<Question> findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Question entity) {
        return false;
    }

    @Override
    public boolean create(Question entity) {
        return false;
    }

    @Override
    public boolean update(Question entity) {
        return false;
    }

    @Override
    public void close(Statement statement) throws DaoException {
        super.close(statement);
    }
}
