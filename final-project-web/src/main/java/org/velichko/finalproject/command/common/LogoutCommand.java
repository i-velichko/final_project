package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.controller.Router;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setPagePath(PageConstant.INDEX_PAGE);
        return router;
    }
}
