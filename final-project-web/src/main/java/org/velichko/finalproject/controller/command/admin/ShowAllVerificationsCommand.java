package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.Page;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;

import static org.velichko.finalproject.controller.command.PageName.SHOW_ALL_VERIFICATIONS;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.logic.dao.BaseDao.PAGE_SIZE;

/**
 * @author Ivan Velichko
 *
 * The type Show all verifications command.
 */
public class ShowAllVerificationsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final VerificationService verificationService;

    /**
     * Instantiates a new Show all verifications command.
     *
     * @param verificationService the verification service
     */
    public ShowAllVerificationsCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            int pageToDisplay = getPage(request);
            List<Verification> verifications = verificationService.readByPage(pageToDisplay);
            int verificationCount = verificationService.getVerificationCount();
            request.setAttribute(VERIFICATION_LIST_PARAM, verifications);
            request.setAttribute(PAGEABLE, new Page(verificationCount, pageToDisplay, PAGE_SIZE));
            router.setPagePath(SHOW_ALL_VERIFICATIONS);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error with loading verifications from db", e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;

    }
}
