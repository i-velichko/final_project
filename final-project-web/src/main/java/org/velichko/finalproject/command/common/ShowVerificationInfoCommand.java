package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.command.PageName.VERIFICATION_INFO;
import static org.velichko.finalproject.command.ParamName.*;

public class ShowVerificationInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;

    public ShowVerificationInfoCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String verificationId = request.getParameter(VERIFICATION_ID_PARAM);
        Verification verification = null;
        Optional<Verification> optionalVerification;
        try {
            optionalVerification = verificationService.findVerificationById(Long.parseLong(verificationId));
            if (optionalVerification.isPresent()) {
                List<? extends Number> scores = Arrays.asList(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0);
                request.setAttribute(SCORES_PARAM, scores);
                verification = optionalVerification.get();
                request.setAttribute(VERIFICATION_PARAM, verification);
                router.setPagePath(VERIFICATION_INFO);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with download verification page with this verification: " + verification);
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
