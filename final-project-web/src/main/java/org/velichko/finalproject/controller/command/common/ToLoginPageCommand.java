package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.command.PageName.LOGIN_PAGE;

public class ToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(LOGIN_PAGE);
        return router;
    }
}
