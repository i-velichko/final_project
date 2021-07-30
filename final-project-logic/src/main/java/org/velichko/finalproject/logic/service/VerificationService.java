package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.List;

public interface VerificationService {
    List<Verification> readAll () throws ServiceException;

    boolean createNewVerification(Verification verification, String title) throws ServiceException;
}
