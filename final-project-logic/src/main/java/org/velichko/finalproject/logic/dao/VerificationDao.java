package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface VerificationDao extends BaseDao<Long, Verification> {

    boolean createNewVerification(Verification user, String projectName) throws DaoException;

    Optional<Verification> findVerificationByStudentId(Long id) throws DaoException;

    boolean changeTrainerScoreById(long id, double score) throws DaoException;

    boolean changeTrainerCharacteristicById(long id, String characteristic) throws DaoException;

    boolean changeVerificationStatusById(long id, VerificationStatus finalStatus) throws DaoException;

    boolean changeFinalVerificationStatusById(long id, FinalStatus status) throws DaoException;

    boolean changeTrainerVerificationDateById(long id, String dateTime) throws DaoException;

    boolean changeExaminerVerificationDateById(long id, String dateTime) throws DaoException;


}
