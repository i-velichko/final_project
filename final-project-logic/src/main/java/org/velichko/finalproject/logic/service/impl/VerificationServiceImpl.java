package org.velichko.finalproject.logic.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.dao.impl.VerificationDaoImpl;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;

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
}
