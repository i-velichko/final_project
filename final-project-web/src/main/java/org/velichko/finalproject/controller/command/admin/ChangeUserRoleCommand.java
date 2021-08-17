package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Change user role command.
 */
public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    /**
     * Instantiates a new Change user role command.
     *
     * @param userService the user service
     */
    public ChangeUserRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        String newRole = request.getParameter(NEW_ROLE);
        try {
            UserRole role = UserRole.valueOf(newRole);
            userService.changeUserRole(Long.parseLong(userId), role);
        } catch (ServiceException | IllegalArgumentException e) {
            logger.log(Level.ERROR, "Error. Impossible change role by this" + userId + "user", e); //todo
            request.setAttribute(MSG, e.getMessage());//TODO
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}

