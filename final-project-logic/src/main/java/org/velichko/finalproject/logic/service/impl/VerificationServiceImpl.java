package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;
import java.util.Optional;

public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationDao verificationDao;

    public VerificationServiceImpl(VerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    @Override
    public List<Verification> findAllVerifications() throws ServiceException {
        List<Verification> verifications;
        try {
            verifications = verificationDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Verifications .", e);
            throw new ServiceException("Error with find all Verifications .", e);
        }
        return verifications;
    }

    @Override
    public boolean createNewVerification(Verification verification, String title) throws ServiceException {
        boolean result;
        try {
            verificationDao.createNewVerification(verification, title);
            result = true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with create new Verification. ", e);
            throw new ServiceException("Error with create new Verification. ", e);
        }
        return result;
    }

    @Override
    public Optional<Verification> findVerificationById(long id) throws ServiceException {
        Optional<Verification> optionalVerification;
        Verification verification = null;
        try {
            optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verification = optionalVerification.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find verification by id " + id, e);
            throw new ServiceException("Error with find verification by ID " + id, e);
        }
        return Optional.ofNullable(verification);
    }

    @Override
    public Optional<Verification> findVerificationByStudentId(long id) throws ServiceException {
        Optional<Verification> optionalVerification;
        Verification verification = null;
        try {
            optionalVerification = verificationDao.findVerificationByStudentId(id);
            if (optionalVerification.isPresent()) {
                verification = optionalVerification.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find verification by student id " + id, e);
            throw new ServiceException("Error with find verification by student ID " + id, e);
        }
        return Optional.ofNullable(verification);
    }

    @Override
    public boolean changeTrainerScore(Long verificationId, Double newScore) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerScoreById(verificationId, newScore);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed trainer score", e);
            throw new ServiceException("Impossible changed trainer score", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeTrainerVerificationDateById(Long verificationId, String dateTime) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerVerificationDateById(verificationId, dateTime);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed trainer verification date", e);
            throw new ServiceException("Impossible changed trainer verification date", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeExaminerVerificationDateById(Long verificationId, String dateTime) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeExaminerVerificationDateById(verificationId, dateTime);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed examiner verification date", e);
            throw new ServiceException("Impossible changed examiner verification date", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeTrainerCharacteristicById(long id, String characteristic) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerCharacteristicById(id, characteristic);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed examiner verification date", e);
            throw new ServiceException("Impossible change trainer characteristic", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verificationDao.changeFinalVerificationStatusById(id, finalStatus);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed final status", e);
            throw new ServiceException("Impossible change final status", e);
        }
        return isChanged;
    }

    @Override
    public List<Verification> findAllApprovedVerifications(VerificationStatus verificationStatus) {
        return null;
    }

    @Override
    public boolean changeVerificationStatusById(Long verificationId, VerificationStatus verificationStatus) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeVerificationStatusById(verificationId, verificationStatus);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed verification status", e);
            throw new ServiceException("Impossible change verification status", e);
        }
        return isChanged;
    }

}
