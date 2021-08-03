package org.velichko.finalproject.validator;

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
    private final Logger logger = LogManager.getLogger();
    private final UserService service = UserServiceImpl.getInstance();
    private final I18nManager i18n = new I18nManager(); //TODO fix new

    public Map<String, String> checkValues(Map<String, String> registrationData, String locale) {

        Map<String, String> result = new HashMap<>();

        String login = registrationData.get(LOGIN_PARAM);

        if (login != null && login.matches(LOGIN.getRegExp())) {
            try {
                if (!service.isLoginUnique(login)) {
                    result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_UNIQUE_KEY, locale));
                }
            } catch (ServiceException e) {
                e.printStackTrace(); //todo
            }
        } else {
            result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
        }


        String email = registrationData.get(EMAIL_PARAM);
        if (email != null && email.matches(EMAIL.getRegExp())) {
            try {
                if (!service.isEmailUnique(email)) {
                    result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_UNIQUE_KEY, locale));
                }
            } catch (ServiceException e) {
                e.printStackTrace();//todo
            }
        } else {
            result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_CORRECT_KEY, locale));
        }


        String password = registrationData.get(PASSWORD_PARAM);
        String confirmPassword = registrationData.get(CONFIRM_PASSWORD_PARAM);
        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (!password.equals(confirmPassword)) {
                result.put(PASSWORD_ERROR_PARAM, i18n.getMassage(PASSWORD_NOT_CORRECT_KEY, locale)); //todo ошибка повторных паролей
            }
        } else {
            result.put(PASSWORD_ERROR_PARAM, i18n.getMassage(PASSWORD_NOT_CORRECT_KEY, locale));
        }

        return result;
    }


    private static class RegistrationDataValidatorHolder {
        public static final RegistrationDataValidator HOLDER_INSTANCE = new RegistrationDataValidator();
    }

    public static RegistrationDataValidator getInstance() {
        return RegistrationDataValidator.RegistrationDataValidatorHolder.HOLDER_INSTANCE;
    }
}
