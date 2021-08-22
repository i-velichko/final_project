package org.velichko.finalproject.validator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.MessageNameKey.*;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.validator.impl.UserDataValidator.*;

/**
 * author Ivan Velichko
 * .
 * The type Verification data validator.
 */
public class VerificationDataValidator implements BaseDataValidator {
    private static final Logger logger = LogManager.getLogger(); //todo
    private final UserService userservice;
    private final I18nManager i18n;

    /**
     * Instantiates a new Verification data validator.
     *
     * @param userservice the userservice
     * @param i18n        the 18 n
     */
    public VerificationDataValidator(UserService userservice, I18nManager i18n) {
        this.userservice = userservice;
        this.i18n = i18n;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> verificationData, String locale) {
        Map<String, String> result = new HashMap<>();
        String gitLink = verificationData.get(GIT_LINK_PARAM);
        String projectTitle = verificationData.get(PROJECT_TITLE_PARAM);

        if (gitLink != null && gitLink.matches(GIT_LINK.getRegExp())) {
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

        if (projectTitle == null || !projectTitle.matches(PROJECT_TITLE.getRegExp())) {
            result.put(PROJECT_TITLE_ERROR_PARAM, i18n.getMassage(PROJECT_TITLE_NOT_CORRECT_KEY, locale));
        }

        return result;
    }

}
