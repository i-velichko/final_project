package org.velichko.finalproject.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

public class ChangeUserStatusCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String newStatus = request.getParameter("new_status");
        UserStatus status = UserStatus.valueOf(newStatus);
        UserService userService = new UserServiceImpl();
        try {
            userService.userStatusController(Long.parseLong(userId), status);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change status by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
