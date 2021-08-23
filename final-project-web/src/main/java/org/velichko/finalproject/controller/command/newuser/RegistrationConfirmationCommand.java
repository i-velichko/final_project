package org.velichko.finalproject.controller.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.PageName.LOGIN_PAGE;
import static org.velichko.finalproject.controller.command.PageName.REGISTRATION_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.ERROR_MESSAGE;

/**
 * @author Ivan Velichko
 *
 * The type Registration confirmation command.
 */
public class RegistrationConfirmationCommand implements Command {
    private final UserService userService;

    /**
     * Instantiates a new Registration confirmation command.
     *
     * @param userService the user service
     */
    public RegistrationConfirmationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String confirmationKey = request.getParameter(ParamName.CONFIRM_KEY);
        try {
            Optional<User> userOptional = userService.getUserByRegistrationKey(confirmationKey);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.changeUserStatus(user.getId(), UserStatus.ACTIVE);
                router.setPagePath(LOGIN_PAGE);
            } else {
                router.setPagePath(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "error with confirmation registration", e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
