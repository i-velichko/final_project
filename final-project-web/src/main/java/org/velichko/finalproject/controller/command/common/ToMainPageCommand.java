package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.Router;

public class ToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PageName.MAIN_PAGE);
        return router;
    }
}
