package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.Optional;

import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.MessageNameKey.*;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService;
    private final I18nManager i18nManager;

    public LoginCommand(UserService userService, I18nManager i18nManager) {
        this.userService = userService;
        this.i18nManager = i18nManager;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER_PARAM);
        if (user != null) {
            request.setAttribute(USER_PARAM, user);
            router.setRouterType(REDIRECT);
            router.setPagePath(MAIN_PAGE);
        } else {
            getUserViaDB(request, router);
        }

        return router;
    }

    private void getUserViaDB(HttpServletRequest request, Router router) {
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {
            try {
                Optional<User> currentUser = userService.findUserByLoginAndPassword(login, password);
                if (currentUser.isPresent()) {
                    User user = currentUser.get();
                    request.getSession().setAttribute(USER_PARAM, user);
                    request.setAttribute(USER_PARAM, user);
                    router.setRouterType(REDIRECT);
                    router.setPagePath(REDIRECT_TO_MAIN_PAGE);
                } else {
                    request.setAttribute(USER_NOT_FOUND_PARAM, i18nManager.getMassage(LOGIN_NOT_CORRECT_KEY, locale));
                    router.setPagePath(LOGIN_PAGE);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Error with find user by login: " + login);
            }
        } else {
            router.setPagePath(LOGIN_PAGE);
        }
    }
}


