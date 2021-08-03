package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VerificationDao {

    boolean createNewVerification(Verification user, String projectName) throws DaoException;

    boolean changeTrainerScoreById(long id, double score) throws DaoException;

    boolean changeTrainerCharacteristicById(long id, String characteristic) throws DaoException;

    boolean changeVerificationStatusById(long id, VerificationStatus finalStatus) throws DaoException;

    boolean changeFinalVerificationStatusById(long id, FinalStatus status) throws DaoException;

    boolean changeTrainerVerificationDateById(long id, String dateTime) throws DaoException;

    boolean changeExaminerVerificationDateById(long id, String dateTime) throws DaoException;


    Verification findVerificationByVerificationStatusId(long id);

    Verification findVerificationByFinalStatusId(long id);

    Verification findVerificationByStudentId(long id);

    List<Verification> findVerificationsByTrainerScore(long id);

    Optional<Verification> findVerificationByGitLink(String gitLink);
}
