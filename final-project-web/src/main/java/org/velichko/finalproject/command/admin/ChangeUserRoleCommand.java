package org.velichko.finalproject.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

public class ChangeUserRoleCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String newRole = request.getParameter("new_role");
        UserRole role = UserRole.valueOf(newRole);
        UserService userService = new UserServiceImpl();
        try {
            userService.userRoleController(Long.parseLong(userId), role);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change role by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}

