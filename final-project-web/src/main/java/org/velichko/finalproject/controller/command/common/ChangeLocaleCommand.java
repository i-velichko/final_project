package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;

import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.PageName.REFERER;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Change locale command.
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setRouterType(REDIRECT);
        HttpSession session = request.getSession();
        String locale = (String) request.getAttribute(LOCALE_PARAM);
        if (locale == null || RU_LOCALE.equals(locale)) {
            locale = EN_LOCALE;
        } else {
            locale = RU_LOCALE;
        }
        session.setAttribute(LOCALE_PARAM, locale);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
