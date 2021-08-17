package org.velichko.finalproject.validator.impl;

import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.MessageNameKey.*;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.validator.impl.UserDataValidator.EMAIL;

public class EditUserDataValidator implements BaseDataValidator {
    private static final String URL_REGEXP = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
    private static final String NAME_REGEXP = "^.{1,30}$";
    private final UserService userservice;
    private final I18nManager i18n;

    public EditUserDataValidator(UserService userservice, I18nManager i18n) {
        this.userservice = userservice;
        this.i18n = i18n;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> editUserData, String locale) {
        Map<String, String> result = new HashMap<>();
        String firstName = editUserData.get(FIRST_NAME_PARAM);
        String lastName = editUserData.get(LAST_NAME_PARAM);
        String gitLink = editUserData.get(GIT_LINK_PARAM);
        String email = editUserData.get(EMAIL_PARAM);

        if (gitLink != null && gitLink.matches(URL_REGEXP)) {
            try {
                if (!userservice.isGitLinkUnique(gitLink)) {
                    result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_UNIQUE_KEY, locale));
                }
            } catch (ServiceException e) {
                e.printStackTrace(); //todo
            }
        } else {
            result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_CORRECT_KEY, locale));
        }


        if (email != null && email.matches(EMAIL.getRegExp())) {
            try {
                if (!userservice.isEmailUnique(email)) {
                    result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_UNIQUE_KEY, locale));
                }
            } catch (ServiceException e) {
                e.printStackTrace();//todo
            }
        } else {
            result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_CORRECT_KEY, locale));
        }


        if (firstName == null || !firstName.matches(NAME_REGEXP)) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }

        if (lastName == null || !lastName.matches(NAME_REGEXP)) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }


        return result;
    }
}
