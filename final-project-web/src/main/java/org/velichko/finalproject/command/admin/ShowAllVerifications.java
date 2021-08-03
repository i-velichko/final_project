package org.velichko.finalproject.command.admin;

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

import java.util.List;

import static org.velichko.finalproject.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.command.PageName.SHOW_ALL_VERIFICATIONS;
import static org.velichko.finalproject.command.ParamName.VERIFICATION_LIST_PARAM;

public class ShowAllVerifications implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationService service = VerificationServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<Verification> verifications = null;
        try {
            verifications = service.findAllVerifications();
            request.setAttribute(VERIFICATION_LIST_PARAM, verifications);
            router.setPagePath(SHOW_ALL_VERIFICATIONS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while client registration data", e); //todo
            router.setPagePath(ERROR_PAGE);
        }
        return router;

    }
}
