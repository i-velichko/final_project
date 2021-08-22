package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.PageName.ERROR_PAGE;

public class WrongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setRouterType(REDIRECT);
        router.setPagePath(ERROR_PAGE);


        return router;
    }
}
