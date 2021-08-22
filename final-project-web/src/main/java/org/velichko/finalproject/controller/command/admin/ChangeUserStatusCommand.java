package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import static org.velichko.finalproject.controller.command.ParamName.*;

public class ChangeUserStatusCommand implements Command {
    private final Logger LOGGER = LogManager.getLogger();
    private final UserService userService;


    public ChangeUserStatusCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        String newStatus = request.getParameter(NEW_STATUS_PARAM);
        try {
            UserStatus status = UserStatus.valueOf(newStatus);
            userService.changeUserStatus(Long.parseLong(userId), status);
        } catch (ServiceException | IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Error. Impossible change status by this " + userId + " user", e); //todo
            request.setAttribute(MSG, e.getMessage());//TODO
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
