package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.velichko.finalproject.command.PageName.STUDENT_INFO;
import static org.velichko.finalproject.command.ParamName.USER_PARAM;

public class ShowStudentInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter("userId");
        User user;
        Optional<User> currentUser;

        try {
            currentUser = service.findUserById(Long.parseLong(userId));
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.setAttribute(USER_PARAM, user);
                router.setPagePath(STUDENT_INFO);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with find user by id: " + userId);
        }
        return router;
    }
}
