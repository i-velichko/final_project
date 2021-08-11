package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.exception.ServiceException;

public interface EmailService {
    boolean sendEmail (String emailTo, String messageContent) throws ServiceException;
}
