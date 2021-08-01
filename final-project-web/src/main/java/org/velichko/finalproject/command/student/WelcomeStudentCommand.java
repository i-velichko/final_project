package org.velichko.finalproject.command.student;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.velichko.finalproject.command.PageName.*;
import static org.velichko.finalproject.command.ParamName.*;

public class WelcomeStudentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User currentUser = (User) request.getSession().getAttribute(USER_PARAM);
        request.setAttribute(USER_PARAM, currentUser);
        List<User> users;
        try {
            users = service.readAll();
            List<User> trainers = users.stream()
                    .filter(user -> user.getRole().equals(UserRole.TRAINER))
                    .collect(Collectors.toList());
            request.setAttribute(TRAINER_LIST_PARAM, trainers);
            router.setPagePath(WELCOME_STUDENT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while client registration data", e); //todo
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
