package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface VerificationService {
    List<Verification> findAllVerifications() throws ServiceException;

    boolean createNewVerification(Verification verification, String title) throws ServiceException;

    Optional<Verification> findVerificationById(long id) throws ServiceException;

    boolean changeTrainerScore(Long verificationId, Double newScore) throws ServiceException;

    boolean changeTrainerVerificationDateById(Long verificationId, String dateTime) throws ServiceException;

    boolean changeExaminerVerificationDateById(Long verificationId, String dateTime) throws ServiceException;

    boolean changeTrainerCharacteristicById(long id, String characteristic) throws ServiceException;

    boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws ServiceException;

    List<Verification> findAllApprovedVerifications(VerificationStatus status);

    boolean changeVerificationStatusById(Long verificationId, VerificationStatus verificationStatus) throws ServiceException;
}
