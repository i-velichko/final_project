package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;

import static org.velichko.finalproject.command.PageName.REFERER;
import static org.velichko.finalproject.command.ParamName.*;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setRouterType(Router.RouterType.REDIRECT);
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(LOCALE_PARAM);
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
