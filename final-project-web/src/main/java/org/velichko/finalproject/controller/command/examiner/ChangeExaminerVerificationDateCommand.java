package org.velichko.finalproject.controller.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import static jakarta.servlet.http.HttpServletResponse.*;
import static org.velichko.finalproject.controller.command.ParamName.ERROR_MESSAGE;

/**
 * @author Ivan Velichko
 *
 * The type Change examiner verification date command.
 */
public class ChangeExaminerVerificationDateCommand implements Command {
    
    private final VerificationService verificationService;

    /**
     * Instantiates a new Change examiner verification date command.
     *
     * @param verificationService the verification service
     */
    public ChangeExaminerVerificationDateCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Long verificationId = Long.parseLong(request.getParameter(ParamName.VERIFICATION_ID_PARAM));
        String dateTime = request.getParameter(ParamName.DATE_TIME_PARAM);
        try {
            verificationService.changeExaminerVerificationDateById(verificationId, dateTime);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Error. Impossible change examiner verification date by this " + verificationId + " verification");
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
