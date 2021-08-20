package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.command.PageName.ADD_USER_PAGE;

/**
 * @author Ivan Velichko
 * @date 19.08.2021 23:36
 */
public class ToAddUserPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(ADD_USER_PAGE);
        return router;
    }
}
