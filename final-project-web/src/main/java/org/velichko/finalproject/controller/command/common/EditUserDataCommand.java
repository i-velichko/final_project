package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

public class EditUserDataCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final BaseDataValidator dataValidator;

    public EditUserDataCommand(UserService userService, BaseDataValidator dataValidator) {
        this.userService = userService;
        this.dataValidator = dataValidator;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER_PARAM);
        String newFirstName = request.getParameter(FIRST_NAME_PARAM);
        String newLastName = request.getParameter(LAST_NAME_PARAM);
        String newGitLink = request.getParameter(GIT_LINK_PARAM);
        String newEmail = request.getParameter(EMAIL_PARAM);
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);

        Map<String, String> newUserData = new HashMap<>();
        String currentUserEmail = user.getEmail();
        String currentUserGitLink = user.getGitLink();

        if (!currentUserEmail.equals(newEmail)) {
            newUserData.put(EMAIL_PARAM, newEmail);
        } else {
            newUserData.put(OLD_EMAIL, currentUserEmail);
        }
        if (!currentUserGitLink.equals(newGitLink)) {
            newUserData.put(GIT_LINK_PARAM, newGitLink);
        } else {
            newUserData.put(OLD_GIT_LINK, currentUserGitLink);
        }
        newUserData.put(FIRST_NAME_PARAM, newFirstName);
        newUserData.put(LAST_NAME_PARAM, newLastName);

        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {
            Map<String, String> errors = dataValidator.checkValues(newUserData, locale);
            if (!errors.isEmpty()) {
                request.setAttribute(CORRECT_EDIT_DATA_PARAM, newUserData);
                request.setAttribute(ERROR_EDIT_DATA_PARAM, errors);
                router.setPagePath(EDIT_USER_DATA);
            } else {
                user.setFirstName(newFirstName);
                user.setLastName(newLastName);
                user.setEmail(newEmail);
                user.setGitLink(newGitLink);
                router.setPagePath(EDIT_USER_DATA);
                try {
                    userService.updateUser(user);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Error with update information, try again", e); //todo
                    request.setAttribute(MSG, e.getMessage());//TODO
                    router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                router.setRouterType(Router.RouterType.REDIRECT);
                router.setPagePath(REDIRECT_TO_EDIT_USER_DATA_PAGE);
            }
        } else {
            router.setPagePath(EDIT_USER_DATA);
        }
        return router;
    }
}
