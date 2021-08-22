package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

public class WrongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return router;
    }
}
