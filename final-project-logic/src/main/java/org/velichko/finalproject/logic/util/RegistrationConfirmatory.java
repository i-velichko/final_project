package org.velichko.finalproject.logic.util;

import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;

/**
 * @author Ivan Velichko
 *
 * The type Registration confirmatory.
 */
public class RegistrationConfirmatory {
    private static final String MESSAGE_TO_CONFIRM_REGISTRATION = "Click to confirm email: <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=registration_confirmation_command&confirm=";
    private final EmailService emailService;

    /**
     * Instantiates a new Registration confirmatory.
     *
     * @param emailService the email service
     */
    public RegistrationConfirmatory(EmailService emailService) {
        this.emailService = emailService;
    }


    /**
     * Sets registration token.
     *
     * @param email the email
     * @param key   the key
     * @return the registration token
     * @throws ServiceException the service exception
     */
    public String sendEmailForConfirmRegistration(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);
        String message = MESSAGE_TO_CONFIRM_REGISTRATION + registrationKey +
                ">link</a>";
        emailService.sendEmail(email, message);
        return registrationKey;
    }
}
