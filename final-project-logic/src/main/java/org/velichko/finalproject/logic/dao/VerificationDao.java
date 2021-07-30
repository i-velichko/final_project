package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.List;

public interface VerificationDao {

    public boolean createNewVerification(Verification user, String projectName) throws DaoException;

    Verification findVerificationByVerificationStatusId(long id);

    Verification findVerificationByFinalStatusId(long id);

    Verification findVerificationByStudentId(long id);

    List<Verification> findVerificationsByTrainerScore(long id);
}
