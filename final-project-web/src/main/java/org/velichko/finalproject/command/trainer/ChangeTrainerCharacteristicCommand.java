package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class ChangeTrainerCharacteristicCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService = VerificationServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String characteristic = request.getParameter(CHARACTERISTIC_PARAM);
        long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        try {
            verificationService.changeTrainerCharacteristicById(verificationId, characteristic);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change characteristic by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
