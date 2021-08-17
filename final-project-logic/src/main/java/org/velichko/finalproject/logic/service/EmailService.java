package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.exception.ServiceException;

/**
 * @author Ivan Velichko
 *
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send email boolean.
     *
     * @param emailTo        the email to
     * @param messageContent the message content
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean sendEmail (String emailTo, String messageContent) throws ServiceException;
}
