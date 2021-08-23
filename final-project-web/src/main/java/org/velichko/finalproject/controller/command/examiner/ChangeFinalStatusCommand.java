package org.velichko.finalproject.controller.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 * 
 * The type Change final status command.
 */
public class ChangeFinalStatusCommand implements Command {
    
    private final VerificationService verificationService;

    /**
     * Instantiates a new Change final status command.
     *
     * @param verificationService the verification service
     */
    public ChangeFinalStatusCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String verificationId = request.getParameter(VERIFICATION_ID_PARAM);
        String newFinalStatus = request.getParameter(NEW_FINAL_STATUS_PARAM);

        try {
            FinalStatus finalStatus = FinalStatus.valueOf(newFinalStatus);
            verificationService.changeFinalVerificationStatusById(Long.parseLong(verificationId), finalStatus);
        } catch (ServiceException | IllegalArgumentException e) {
            LOGGER.log(Level.DEBUG, "Error. Impossible change final status by this " + verificationId + " verification");
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
