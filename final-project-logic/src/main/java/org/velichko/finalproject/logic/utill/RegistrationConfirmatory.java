package org.velichko.finalproject.logic.utill;

import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

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
