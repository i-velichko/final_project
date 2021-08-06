package org.velichko.finalproject.logic.util;

import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;

public class RegistrationConfirmatory {
    private final EmailService emailService;

    public RegistrationConfirmatory(EmailService emailService) {
        this.emailService = emailService;
    }

    public String setRegistrationToken(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);

        emailService.sendEmail(email, registrationKey);
        return registrationKey;
    }


}
