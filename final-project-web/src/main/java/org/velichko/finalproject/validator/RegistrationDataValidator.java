package org.velichko.finalproject.validator;

import org.apache.logging.log4j.Level;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.command.MessageNameKey.LOGIN_NOT_CORRECT_KEY;
import static org.velichko.finalproject.command.MessageNameKey.LOGIN_NOT_UNIQUE_KEY;
import static org.velichko.finalproject.command.ParamName.LOGIN_ERROR_PARAM;
import static org.velichko.finalproject.command.ParamName.LOGIN_PARAM;
import static org.velichko.finalproject.validator.DataUserValidator.LOGIN;

public class RegistrationDataValidator {
    public static Map<String, String> checkValues(Map<String, String> registrationData, String locale) throws ServiceException {
        UserService service = new UserServiceImpl();
        I18nManager i18n = I18nManager.getInstance();
        Map<String, String> result = new HashMap<>();
        boolean isError = false;
        String login = registrationData.get(LOGIN_PARAM);
        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (service.isLoginUnique(login)) {
                result.put(LOGIN_PARAM, login);
            } else {
                result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_UNIQUE_KEY, locale));
                isError = true;
            }
        } else {
            result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
            isError = true;
        }
        if (!isError) {
            result.clear();
        }
        return result;
    }
}
