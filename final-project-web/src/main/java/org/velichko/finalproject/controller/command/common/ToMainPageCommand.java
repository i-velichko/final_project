package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.command.PageName.MAIN_PAGE;

public class ToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(MAIN_PAGE);
        return router;
    }
}
