package org.velichko.finalproject.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.command.MessageNameKey.*;
import static org.velichko.finalproject.command.ParamName.*;

public class VerificationDataValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final VerificationService verificationService = new VerificationServiceImpl();
    private static final I18nManager i18n = I18nManager.getInstance();
    private static final String URL_REGEXP = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
    private static final String PROJECT_TITLE_REGEXP = "^.{1,100}$";

    public static Map<String, String> checkValues(Map<String, String> verificationData, String locale) {
        Map<String, String> result = new HashMap<>();
        String gitLink = verificationData.get(GIT_LINK);
        String projectTitle = verificationData.get(PROJECT_TITLE_PARAM);

        if (gitLink != null && gitLink.matches(URL_REGEXP)) {
            try {
                if (!verificationService.isGitLinkUnique(gitLink)) {
                    result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_UNIQUE_KEY, locale));
                }
            } catch (ServiceException e) {
                e.printStackTrace(); //todo
            }
        } else {
            result.put(GIT_LINK_ERROR_PARAM, i18n.getMassage(GIT_LINK_NOT_CORRECT_KEY, locale));
        }

        if (projectTitle == null || !projectTitle.matches(PROJECT_TITLE_REGEXP)) {
            result.put(PROJECT_TITLE_ERROR_PARAM, i18n.getMassage(PROJECT_TITLE_NOT_CORRECT_KEY, locale));
        }

        return result;
    }

}
