package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.velichko.finalproject.command.PageName.TRAINER_INFO;
import static org.velichko.finalproject.command.ParamName.*;

public class ShowTrainerInfoCommand implements Command {
    private final UserService userService;

    public ShowTrainerInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        final Router router = new Router();
        User user = null;
        Optional<User> currentUser;
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        try {
            currentUser = userService.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.setAttribute(USER_PARAM, user);
                router.setPagePath(TRAINER_INFO);
            }
        } catch (ServiceException e) {
            e.printStackTrace(); //todo
        }


        return router;
    }
}
