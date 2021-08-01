package org.velichko.finalproject.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.*;
import static org.velichko.finalproject.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.command.PageName.STUDENT_INFO;
import static org.velichko.finalproject.command.ParamName.VERIFICATION_PARAM;

public class ShowVerificationInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService = new VerificationServiceImpl();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String verificationId = request.getParameter("verificationId");
        Verification verification = null;
        Optional<Verification> optionalVerification;
        try {
            optionalVerification = verificationService.findVerificationById(Long.parseLong(verificationId));
            if (optionalVerification.isPresent()) {
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
