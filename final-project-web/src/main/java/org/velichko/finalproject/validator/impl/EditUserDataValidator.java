package org.velichko.finalproject.validator.impl;

import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.MessageNameKey.*;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.validator.impl.UserDataValidator.*;
import static org.velichko.finalproject.validator.impl.UserDataValidator.EMAIL;

/**
 * @author Ivan Velichko
 *
 * The type Edit user data validator.
 */
public class EditUserDataValidator implements BaseDataValidator {
    private final UserService userservice;
    private final I18nManager i18n;

    /**
     * Instantiates a new Edit user data validator.
     *
     * @param userservice the userservice
     * @param i18n        the 18 n
     */
    public EditUserDataValidator(UserService userservice, I18nManager i18n) {
        this.userservice = userservice;
        this.i18n = i18n;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> editUserData, String locale) throws ServiceException {
        Map<String, String> result = new HashMap<>();
        String firstName = editUserData.get(FIRST_NAME_PARAM);
        String lastName = editUserData.get(LAST_NAME_PARAM);
        String gitLink = editUserData.get(GIT_LINK_PARAM);
        String oldGitLinK = editUserData.get(OLD_GIT_LINK);
        String email = editUserData.get(EMAIL_PARAM);
        String oldEmail = editUserData.get(OLD_EMAIL);

        if (oldGitLinK == null) {
            if (gitLink != null && gitLink.matches(GIT_LINK.getRegExp())) {
                if (!userservice.isGitLinkUnique(gitLink)) {
                    result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_UNIQUE_KEY, locale));
                }
            } else {
                result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_CORRECT_KEY, locale));
            }
        }

        if (oldEmail == null) {
            if (email != null && email.matches(EMAIL.getRegExp())) {
                if (!userservice.isEmailUnique(email)) {
                    result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_UNIQUE_KEY, locale));
                }
            } else {
                result.put(EMAIL_ERROR_PARAM, i18n.getMassage(EMAIL_NOT_CORRECT_KEY, locale));
            }
        }

        if (firstName == null || !firstName.matches(FIRST_NAME.getRegExp())) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }

        if (lastName == null || !lastName.matches(LAST_NAME.getRegExp())) {
            result.put(NAME_ERROR_PARAM, i18n.getMassage(NAME_NOT_CORRECT_KEY, locale));
        }


        return result;
    }
}
