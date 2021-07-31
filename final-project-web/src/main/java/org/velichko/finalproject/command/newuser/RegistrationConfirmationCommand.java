package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.velichko.finalproject.command.PageName.*;

public class RegistrationConfirmationCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(ParamName.CONFIRM_KEY);
        UserService userService = new UserServiceImpl();
        try {
            Optional<User> userOptional = userService.getUserByRegistrationKey(confirmationKey);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.userStatusController(user.getId(), UserStatus.ACTIVE);
                router.setPagePath(LOGIN_PAGE);
            } else {
                router.setPagePath(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
