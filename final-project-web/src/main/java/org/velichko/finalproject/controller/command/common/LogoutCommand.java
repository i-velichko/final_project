package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.Router.RouterType.*;
import static org.velichko.finalproject.controller.command.PageName.REDIRECT_TO_MAIN_PAGE;

/**
 * @author Ivan Velichko
 *
 * The type Logout command.
 */
public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setRouterType(REDIRECT);
        router.setPagePath(REDIRECT_TO_MAIN_PAGE);
        return router;
    }
}
