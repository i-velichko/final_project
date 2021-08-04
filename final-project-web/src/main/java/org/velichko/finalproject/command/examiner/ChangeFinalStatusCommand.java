package org.velichko.finalproject.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class ChangeFinalStatusCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;

    public ChangeFinalStatusCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String verificationId = request.getParameter(VERIFICATION_ID_PARAM);
        String newFinalStatus = request.getParameter(NEW_FINAL_STATUS_PARAM);
        FinalStatus finalStatus = FinalStatus.valueOf(newFinalStatus);

        try {
            verificationService.changeFinalVerificationStatusById(Long.parseLong(verificationId), finalStatus);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change final status by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
