package org.velichko.finalproject.logic.utill;

import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;

public class RegistrationConfirmatory {

    public static String setRegistrationToken(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);
        EmailService emailService = new EmailServiceImpl();
        emailService.sendEmail(email, registrationKey);
        return registrationKey;
    }

}
