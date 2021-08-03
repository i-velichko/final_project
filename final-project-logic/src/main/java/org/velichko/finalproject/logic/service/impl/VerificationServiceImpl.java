package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.dao.impl.VerificationDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.pool.ConnectionPool;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;
import java.util.Optional;

public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LogManager.getLogger();

    private VerificationServiceImpl() {
    }

    private static class VerificationServiceHolder {
        public static final VerificationService HOLDER_INSTANCE = new VerificationServiceImpl();
    }

    public static VerificationService getInstance() {
        return VerificationServiceHolder.HOLDER_INSTANCE;
    }


    @Override
    public List<Verification> findAllVerifications() throws ServiceException {
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        List<Verification> verifications;
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            verifications = verificationDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Verifications .", e);
            throw new ServiceException("Error with find all Verifications .", e);
        } finally {
            transaction.endSingleQuery();
        }
        return verifications;
    }

    @Override
    public boolean createNewVerification(Verification verification, String title) throws ServiceException {
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        boolean result;
        try {
            verificationDao.createNewVerification(verification, title);
            result = true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with create new Verification. ", e);
            throw new ServiceException("Error with create new Verification. ", e);
        } finally {
            transaction.endSingleQuery();
        }
        return result;
    }

    @Override
    public Optional<Verification> findVerificationById(long id) throws ServiceException {
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        Optional<Verification> optionalVerification;
        Verification verification = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verification = optionalVerification.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find verification by id", e);
            throw new ServiceException("Error with find verification by ID " + id, e);
        } finally {
            transaction.endSingleQuery();
        }
        return Optional.ofNullable(verification);
    }

    @Override
    public boolean isGitLinkUnique(String gitLink) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(userDao);
        boolean isPresent = false;
        try {
            Optional<User> optionalVerification = userDao.findUserByGitLink(gitLink);
            if (optionalVerification.isEmpty()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find user project by git link", e);
            throw new ServiceException("Error with find user project by git link", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isPresent;
    }

    @Override
    public boolean changeTrainerScore(Long verificationId, Double newScore) throws ServiceException {
        boolean isChanged = false;
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerScoreById(verificationId, newScore);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed trainer score", e);
            throw new ServiceException("Impossible changed trainer score", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isChanged;
    }

    @Override
    public boolean changeTrainerVerificationDateById(Long verificationId, String dateTime) throws ServiceException {
        boolean isChanged = false;
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerVerificationDateById(verificationId, dateTime);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed trainer verification date", e);
            throw new ServiceException("Impossible changed trainer verification date", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isChanged;
    }

    @Override
    public boolean changeExaminerVerificationDateById(Long verificationId, String dateTime) throws ServiceException {
        boolean isChanged = false;
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(verificationId);
            if (optionalVerification.isPresent()) {
                verificationDao.changeExaminerVerificationDateById(verificationId, dateTime);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed examiner verification date", e);
            throw new ServiceException("Impossible changed examiner verification date", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isChanged;
    }

    @Override
    public boolean changeTrainerCharacteristicById(long id, String characteristic) throws ServiceException {
        boolean isChanged = false;
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verificationDao.changeTrainerCharacteristicById(id, characteristic);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed examiner verification date", e);
            throw new ServiceException("Impossible change trainer characteristic", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isChanged;
    }

    @Override
    public boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws ServiceException {
        boolean isChanged = false;
        VerificationDaoImpl verificationDao = new VerificationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginSingleQuery(verificationDao);
        try {
            Optional<Verification> optionalVerification = verificationDao.findEntityById(id);
            if (optionalVerification.isPresent()) {
                verificationDao.changeFinalVerificationStatusById(id, finalStatus);
                isChanged = true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with changed final status", e);
            throw new ServiceException("Impossible change final status", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isChanged;
    }

    @Override
    public List<Verification> findAllApprovedVerifications(VerificationStatus status) {
        return null;
    }
}
