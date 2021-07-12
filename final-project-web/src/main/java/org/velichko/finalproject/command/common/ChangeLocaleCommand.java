package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setRouterType(Router.RouterType.REDIRECT);

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(ParamConstant.LOCALE_PARAM);

        if (locale == null || "ru-RU".equals(locale)) {
            locale = "en-EN";
        } else {
            locale = "ru-RU";
        }

        session.setAttribute(ParamConstant.LOCALE_PARAM, locale);
        String refererCommand = request.getParameter("refererCommand");

        router.setPagePath(request.getHeader(PageConstant.REFERER_PARAM));

        return router;
    }
}
