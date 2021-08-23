package org.velichko.finalproject.controller.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.PageName.TRAINER_INFO;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Show trainer info command.
 */
public class ShowTrainerInfoCommand implements Command {
    private final UserService userService;

    /**
     * Instantiates a new Show trainer info command.
     *
     * @param userService the user service
     */
    public ShowTrainerInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        final Router router = new Router();
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        try {
            userService.findUserByLoginAndPassword(login, password).ifPresent(user -> {
                request.setAttribute(USER_PARAM, user);
                router.setPagePath(TRAINER_INFO);
            });
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Error with show trainer info ");
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }

        return router;
    }
}
