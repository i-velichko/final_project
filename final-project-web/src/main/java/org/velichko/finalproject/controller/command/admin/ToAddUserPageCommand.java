package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.i18n.I18nManager;

import static org.velichko.finalproject.controller.command.MessageNameKey.ADD_USER_SUCCESSFUL_KEY;
import static org.velichko.finalproject.controller.command.PageName.ADD_USER_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.ADD_USER_IS_DONE;
import static org.velichko.finalproject.controller.command.ParamName.LOCALE_PARAM;


/**
 * @author Ivan Velichko
 *
 * The type To add user page command.
 */
public class ToAddUserPageCommand implements Command {
    /**
     * The 18 n.
     */
    private final I18nManager i18n;

    /**
     * Instantiates a new To add user page command.
     *
     * @param i18n the 18 n
     */
    public ToAddUserPageCommand(I18nManager i18n) {
        this.i18n = i18n;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);
        String message = request.getParameter(ADD_USER_IS_DONE);
        if (message != null) {
            request.setAttribute(ADD_USER_IS_DONE, i18n.getMassage(ADD_USER_SUCCESSFUL_KEY, locale));
        }
        Router router = new Router();
        router.setPagePath(ADD_USER_PAGE);
        return router;
    }
}
