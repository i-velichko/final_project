package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;

import static org.velichko.finalproject.controller.command.CommandName.WRONG_COMMAND;

public class WrongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        request.getSession().setAttribute(ParamName.MESSAGE_PARAM, WRONG_COMMAND);
        router.setPagePath(PageName.ERROR_PAGE);
        router.setRouterType(Router.RouterType.REDIRECT);

        return router;
    }
}
