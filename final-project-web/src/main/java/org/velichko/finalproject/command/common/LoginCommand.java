package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        User user = (User) request.getSession().getAttribute(ParamConstant.USER_PARAM);
        if (user != null) {
            request.setAttribute(ParamConstant.USER_PARAM, user);
            switch (user.getRole()) {
                case STUDENT -> router.setPagePath(PageConstant.WELCOME_STUDENT);
                case TRAINER -> router.setPagePath(PageConstant.WELCOME_TRAINER);
                default -> router.setPagePath(PageConstant.LOGIN_PAGE);
            }

        } else {
            getUserViaDB(request, router);
        }

        return router;
    }

    private void getUserViaDB(HttpServletRequest request, Router router) {
        User user;
        String login = request.getParameter(ParamConstant.LOGIN_PARAM);
        String password = request.getParameter(ParamConstant.PASSWORD_PARAM);

        UserService service = new UserServiceImpl();
        Optional<User> currentUser;
        try {
            currentUser = service.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.getSession().setAttribute(ParamConstant.USER_PARAM, user);
                request.setAttribute(ParamConstant.USER_PARAM, user);
                switch (user.getRole()) {
                    case STUDENT -> router.setPagePath(PageConstant.WELCOME_STUDENT);
                    case TRAINER -> router.setPagePath(PageConstant.WELCOME_TRAINER);
                    default -> router.setPagePath(PageConstant.LOGIN_PAGE);
                }
            } else {
                request.setAttribute(ParamConstant.USER_NOT_FOUND_PARAM, "User not found. Please, register if you want log in.");
                router.setPagePath(PageConstant.LOGIN_PAGE);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with find user by login: " + login);
        }
    }
}


