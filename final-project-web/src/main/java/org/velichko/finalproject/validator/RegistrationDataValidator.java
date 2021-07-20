package org.velichko.finalproject.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.command.MessageNameKey.*;
import static org.velichko.finalproject.command.ParamName.*;
import static org.velichko.finalproject.validator.DataUserValidator.*;

public class RegistrationDataValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService service = new UserServiceImpl();
    private static final I18nManager i18n = I18nManager.getInstance();


    public static Map<String, String> checkValues(Map<String, String> registrationData, String locale) throws ServiceException {

        Map<String, String> result = new HashMap<>();
        boolean isError = false;

        String login = registrationData.get(LOGIN_PARAM);

        if (login == null && login.matches(LOGIN.getRegExp()) {
            if (!service.isLoginUnique(login)) {
                result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_UNIQUE_KEY, locale));
            }
            result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
        }





        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (service.isLoginUnique(login)) {

            } else {
                result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_UNIQUE_KEY, locale));
                isError = true;
            }
        } else {
            result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
            isError = true;
        }


        String email = registrationData.get(EMAIL_PARAM);
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (service.isEmailUnique(email)) {
                result.put(EMAIL_PARAM, email);
            } else {
                result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_UNIQUE_KEY, locale));
                isError = true;
            }
        } else {
            result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_CORRECT_KEY, locale));
            isError = true;
        }


        String password = registrationData.get(PASSWORD_PARAM);
        String confirmPassword = registrationData.get(CONFIRM_PASSWORD_PARAM);
        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (password.equals(CONFIRM_PASSWORD_PARAM)) {
                result.put(PASSWORD_PARAM, password);
            } else {
                logger.log(Level.DEBUG, "The password is incorrect or the passwords do not match ");
                result.put(PASSWORD_ERROR_PARAM, i18n.getMassage(PASSWORD_NOT_CORRECT_KEY, locale));
                isError = true;
            }
        } else {
            result.put(PASSWORD_ERROR_PARAM, i18n.getMassage(PASSWORD_NOT_CORRECT_KEY, locale));
            isError = true;
        }


        String firstName = registrationData.get(FIRST_NAME_PARAM);
        if (firstName != null) {
            result.put(FIRST_NAME_PARAM, firstName);
        }
        String lastName = registrationData.get(LAST_NAME_PARAM);
        if (lastName != null) {
            result.put(LAST_NAME_PARAM, lastName);
        }


        if (!isError) {
            result.clear();
        }
        return result;
    }
}
