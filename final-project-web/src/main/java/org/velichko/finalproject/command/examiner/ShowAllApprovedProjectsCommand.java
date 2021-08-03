package org.velichko.finalproject.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

public class ShowAllApprovedProjectsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService = VerificationServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
//        verificationService.findAllApprovedVerifications();
        return null;
    }
}
