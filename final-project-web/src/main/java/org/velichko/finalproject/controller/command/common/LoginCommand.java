package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.Optional;

import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER_PARAM);
        if (user != null) {
            request.setAttribute(USER_PARAM, user);
            router.setRouterType(REDIRECT);
            switch (user.getRole()) {
                case STUDENT -> router.setPagePath(REDIRECT_STUDENT);
                case TRAINER -> router.setPagePath(WELCOME_TRAINER);
                case EXAMINER -> router.setPagePath(WELCOME_EXAMINER);
                case ADMIN -> router.setPagePath(WELCOME_ADMIN);
                default -> router.setPagePath(LOGIN_PAGE);
            }

        } else {
            getUserViaDB(request, router);
        }

        return router;
    }

    private void getUserViaDB(HttpServletRequest request, Router router) {
        User user;
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        Optional<User> currentUser;
        try {
            currentUser = userService.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.getSession().setAttribute(USER_PARAM, user);
                request.setAttribute(USER_PARAM, user);
                router.setRouterType(REDIRECT);
                router.setPagePath(REDIRECT_MAIN);
            } else {
                request.setAttribute(USER_NOT_FOUND_PARAM, "User not found. Please, register if you want log in."); //todo констант
                router.setPagePath(LOGIN_PAGE);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with find user by login: " + login);
        }
    }
}


