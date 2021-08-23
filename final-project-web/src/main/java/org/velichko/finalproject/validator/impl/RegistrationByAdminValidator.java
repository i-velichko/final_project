package org.velichko.finalproject.validator.impl;

import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.MessageNameKey.*;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.validator.impl.UserDataValidator.*;


/**
 * @author Ivan Velichko
 *
 * The type Registration by admin validator.
 */
public class RegistrationByAdminValidator implements BaseDataValidator {
    private final UserService service;
    private final I18nManager i18n;


    /**
     * Instantiates a new Registration by admin validator.
     *
     * @param service the service
     * @param i18n    the 18 n
     */
    public RegistrationByAdminValidator(UserService service, I18nManager i18n) {
        this.service = service;
        this.i18n = i18n;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> registrationData, String locale) throws ServiceException {

        Map<String, String> result = new HashMap<>();

        String firstName = registrationData.get(FIRST_NAME_PARAM);
        if (firstName == null || !firstName.matches(FIRST_NAME.getRegExp())) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }

        String lastName = registrationData.get(LAST_NAME_PARAM);
        if (lastName == null || !lastName.matches(LAST_NAME.getRegExp())) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }

        String login = registrationData.get(LOGIN_PARAM);

        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (!service.isLoginUnique(login)) {
                result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_UNIQUE_KEY, locale));
            }
        } else {
            result.put(LOGIN_ERROR_PARAM, i18n.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
        }

        String password = registrationData.get(PASSWORD_PARAM);
        if (password == null || !password.matches(PASSWORD.getRegExp())) {
            result.put(PASSWORD_ERROR_PARAM, i18n.getMassage(PASSWORD_NOT_CORRECT_KEY, locale));
        }

        String email = registrationData.get(EMAIL_PARAM);
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (!service.isEmailUnique(email)) {
                result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_UNIQUE_KEY, locale));
            }
        } else {
            result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_CORRECT_KEY, locale));
        }

        String role = registrationData.get(ROLE_PARAM);
        if (role == null || !isInEnum(role)) {
            result.put(ROLE_ERROR_PARAM, i18n.getMassage(ROLE_NOT_CORRECT_KEY, locale));
        }

        return result;
    }

    private boolean isInEnum(String role) {
        return Arrays.asList(UserRole.class.getEnumConstants()).contains(UserRole.valueOf(role));
    }
}
