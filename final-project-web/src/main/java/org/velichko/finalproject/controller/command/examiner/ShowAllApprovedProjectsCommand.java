package org.velichko.finalproject.controller.command.examiner;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.service.VerificationService;

public class ShowAllApprovedProjectsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final VerificationService verificationService;

    public ShowAllApprovedProjectsCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
//        verificationService.findAllApprovedVerifications();
        return null;
    }
}
