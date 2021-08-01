package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.dao.impl.VerificationDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;
import java.util.Optional;

public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Verification> readAll() throws ServiceException {
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
}
