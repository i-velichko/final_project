package org.velichko.finalproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;

public interface Command {

    Logger LOGGER = LogManager.getLogger();

    String PAGE_NUMBER_PARAMETER = "page";

    Router execute(HttpServletRequest request);

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
