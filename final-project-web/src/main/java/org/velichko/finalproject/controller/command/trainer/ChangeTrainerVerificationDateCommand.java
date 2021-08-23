package org.velichko.finalproject.controller.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.controller.command.ParamName.ERROR_MESSAGE;

/**
 * @author Ivan Velichko
 *
 * The type Change trainer verification date command.
 */
public class ChangeTrainerVerificationDateCommand implements Command {

    private final VerificationService verificationService;

    /**
     * Instantiates a new Change trainer verification date command.
     *
     * @param verificationService the verification service
     */
    public ChangeTrainerVerificationDateCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        String dateTime = request.getParameter(DATE_TIME_PARAM);
        try {
            verificationService.changeTrainerVerificationDateById(verificationId, dateTime);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Error. Impossible change trainer verification date by this " + verificationId + " verification");
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
