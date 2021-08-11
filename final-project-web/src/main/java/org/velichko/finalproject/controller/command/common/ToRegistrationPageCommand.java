package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.command.PageName.REGISTRATION_PAGE;

public class ToRegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(REGISTRATION_PAGE);
        return router;
    }
}
