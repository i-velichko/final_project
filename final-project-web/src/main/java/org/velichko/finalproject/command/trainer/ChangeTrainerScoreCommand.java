package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class ChangeTrainerScoreCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        Long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        String newScore = request.getParameter(NEW_SCORE_PARAM);
        String data = request.getParameter("dateReport");
        Double score = Double.parseDouble(newScore);

        VerificationService verificationService = new VerificationServiceImpl();
        try {
            verificationService.changeTrainerScore(verificationId, score);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change new score by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
