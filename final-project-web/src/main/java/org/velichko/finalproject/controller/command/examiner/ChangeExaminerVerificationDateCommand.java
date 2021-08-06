package org.velichko.finalproject.controller.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

public class ChangeExaminerVerificationDateCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;

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
            logger.log(Level.DEBUG, "Error. Impossible change examiner verification date by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
