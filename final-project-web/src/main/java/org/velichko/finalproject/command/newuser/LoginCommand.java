package org.velichko.finalproject.command.newuser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.velichko.finalproject.logic.entity.type.UserRole.STUDENT;
import static org.velichko.finalproject.logic.entity.type.UserRole.TRAINER;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(ParamConstant.LOGIN_PARAM);

        UserService service = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        User user;
        Optional<User> currentUser = null;
        try {
            currentUser = service.findUserByLogin(login);
            if (!(currentUser.get().getLogin() == null)) {
                user = currentUser.get();
                request.setAttribute(ParamConstant.USER_PARAM, user);
                switch (user.getRole()) {
                    case STUDENT -> router.setPagePath(PageConstant.WELCOME_STUDENT);
                    case TRAINER -> router.setPagePath(PageConstant.WELCOME_TRAINER);
                    default -> router.setPagePath(PageConstant.INDEX_PAGE);
                }
            } else {
                request.setAttribute(ParamConstant.USER_NOT_FOUND_PARAM, "User not found. Please, register if you want log in.");
                router.setPagePath(PageConstant.INDEX_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with find user by login: " + login);
        }
        return router;
    }
}


