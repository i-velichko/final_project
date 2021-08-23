package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.i18n.I18nManager;

import static org.velichko.finalproject.controller.command.MessageNameKey.REGISTRATION_SUCCESSFUL_KEY;
import static org.velichko.finalproject.controller.command.PageName.LOGIN_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.LOCALE_PARAM;
import static org.velichko.finalproject.controller.command.ParamName.REGISTRATION_IS_DONE;

/**
 * @author Ivan Velichko
 *
 * The type To login page command.
 */
public class ToLoginPageCommand implements Command {
    private final I18nManager i18n;

    /**
     * Instantiates a new To login page command.
     *
     * @param i18n the 18 n
     */
    public ToLoginPageCommand(I18nManager i18n) {
        this.i18n = i18n;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);
        String message = request.getParameter(REGISTRATION_IS_DONE);
        if (message != null) {
            request.setAttribute(REGISTRATION_IS_DONE, i18n.getMassage(REGISTRATION_SUCCESSFUL_KEY, locale));
        }
        router.setPagePath(LOGIN_PAGE);
        return router;
    }
}
