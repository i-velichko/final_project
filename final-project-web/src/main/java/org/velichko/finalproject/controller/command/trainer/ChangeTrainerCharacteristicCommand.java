package org.velichko.finalproject.controller.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Change trainer characteristic command.
 */
public class ChangeTrainerCharacteristicCommand implements Command {

    private final VerificationService verificationService;

    /**
     * Instantiates a new Change trainer characteristic command.
     *
     * @param verificationService the verification service
     */
    public ChangeTrainerCharacteristicCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String characteristic = request.getParameter(CHARACTERISTIC_PARAM);
        long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        try {
            verificationService.changeTrainerCharacteristicById(verificationId, characteristic);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Error. Impossible change characteristic by this " + verificationId + " verification");
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
