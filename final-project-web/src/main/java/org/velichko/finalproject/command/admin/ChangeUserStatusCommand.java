package org.velichko.finalproject.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class ChangeUserStatusCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final UserService userService;


    public ChangeUserStatusCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        String newStatus = request.getParameter(NEW_STATUS_PARAM);
        UserStatus status = UserStatus.valueOf(newStatus);

        try {
            userService.changeUserStatus(Long.parseLong(userId), status);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change status by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
