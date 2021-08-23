package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.webfacade.VerificationWebFacade;
import org.velichko.finalproject.webfacade.impl.VerificationWebFacadeImpl;

import static jakarta.servlet.http.HttpServletResponse.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Show verification info command.
 */
public class ShowVerificationInfoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final VerificationWebFacade verificationWebFacade;

    /**
     * Instantiates a new Show verification info command.
     *
     * @param verificationWebFacade the verification web facade
     */
    public ShowVerificationInfoCommand(VerificationWebFacade verificationWebFacade) {
        this.verificationWebFacade = verificationWebFacade;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        User user = (User) request.getSession().getAttribute(USER_PARAM);
        UserRole userRole = user.getRole();
        if (verificationId != 0 && userRole != null) {
            try {
                router = verificationWebFacade.getVerificationInfoByVerificationIdAndUserRole(verificationId, request, userRole);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Error with download verification page. ");
                request.setAttribute(ERROR_MESSAGE, e.getMessage());
                router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }

        return router;
    }
}
