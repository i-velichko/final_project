package org.velichko.finalproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;

/**
 * @author Ivan Velichko
 *
 * The interface Command.
 */
public interface Command {

    /**
     * The constant LOGGER.
     */
    Logger LOGGER = LogManager.getLogger();
    /**
     * The constant PAGE_NUMBER_PARAMETER.
     */
    String PAGE_NUMBER_PARAMETER = "page";

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);

    /**
     * Gets page.
     *
     * @param request the request
     * @return the page
     */
    default int getPage(HttpServletRequest request) {
        int page = 1;
        String stringPage = request.getParameter(PAGE_NUMBER_PARAMETER);
        if (stringPage != null) {
            try {
                page = Integer.parseInt(stringPage);
            } catch (NumberFormatException exc) {
                LOGGER.warn("Cannot parse page number {}, use page - 1", stringPage);
            }
        }
        return page;
    }
}
