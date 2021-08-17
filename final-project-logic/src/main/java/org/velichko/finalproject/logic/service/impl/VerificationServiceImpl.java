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

/**
 * @author Ivan Velichko
 *
 * The type Verification service.
 */
public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationDao verificationDao;

    /**
     * Instantiates a new Verification service.
     *
     * @param verificationDao the verification dao
     */
    public VerificationServiceImpl(VerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    @Override
    public List<Verification> findAllVerifications() throws ServiceException {
        try {
            return verificationDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Verifications .", e);
            throw new ServiceException("Error with find all Verifications .", e);
        }
    }

    @Override
    public List<Verification> readByPage(int page) throws ServiceException {
        try {
            return verificationDao.findByPage(page);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Verifications .", e);
            throw new ServiceException("Error with find all Verifications .", e);
        }
    }

    @Override
    public int getVerificationCount() throws ServiceException {
        try {
            return verificationDao.getVerificationCount();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with verifications count .", e);
            throw new ServiceException("Error with verifications count .", e);
        }
    }

    @Override
    public boolean createNewVerification(Verification verification, String title) throws ServiceException {
        try {
            verificationDao.createNewVerification(verification, title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with create new Verification. ", e);
            throw new ServiceException("Error with create new Verification. ", e);
        }
        return true;
    }

    @Override
    public Optional<Verification> findVerificationById(long id) throws ServiceException {
        try {
            return verificationDao.findEntityById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find verification by id " + id, e);
            throw new ServiceException("Error with find verification by ID " + id, e);
        }
    }

    @Override
    public Optional<Verification> findVerificationByStudentId(long id) throws ServiceException {
        try {
            return verificationDao.findVerificationByStudentId(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find verification by student id " + id, e);
            throw new ServiceException("Error with find verification by student ID " + id, e);
        }
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
