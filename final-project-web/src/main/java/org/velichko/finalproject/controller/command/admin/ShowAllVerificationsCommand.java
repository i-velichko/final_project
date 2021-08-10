package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Page;
import org.velichko.finalproject.logic.dao.BaseDao;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.List;

import static org.velichko.finalproject.controller.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.controller.command.PageName.SHOW_ALL_VERIFICATIONS;
import static org.velichko.finalproject.controller.command.ParamName.PAGEABLE;
import static org.velichko.finalproject.controller.command.ParamName.VERIFICATION_LIST_PARAM;
import static org.velichko.finalproject.logic.dao.BaseDao.PAGE_SIZE;

public class ShowAllVerificationsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;

    public ShowAllVerificationsCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            int pageToDisplay = getPage(request);
            List<Verification> verifications = verificationService.readByPage(pageToDisplay);
            int verificationCount = verificationService.getVerificationCount();
            request.setAttribute(VERIFICATION_LIST_PARAM, verifications);
            request.setAttribute(PAGEABLE, new Page(verificationCount, pageToDisplay, PAGE_SIZE));
            router.setPagePath(SHOW_ALL_VERIFICATIONS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with loading verifications from db", e); //todo
            router.setPagePath(ERROR_PAGE);
        }
        return router;

    }
}
