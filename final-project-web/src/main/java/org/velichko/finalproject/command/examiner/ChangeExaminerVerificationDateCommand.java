package org.velichko.finalproject.command.examiner;

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

public class ChangeExaminerVerificationDateCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        Long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        String dateTime = request.getParameter(DATE_TIME_PARAM);
        VerificationService verificationService = new VerificationServiceImpl();
        try {
            verificationService.changeExaminerVerificationDateById(verificationId, dateTime);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change examiner verification date by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
