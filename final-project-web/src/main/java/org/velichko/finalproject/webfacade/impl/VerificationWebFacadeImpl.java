package org.velichko.finalproject.webfacade.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.webfacade.VerificationWebFacade;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.*;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Verification web facade.
 */
public class VerificationWebFacadeImpl implements VerificationWebFacade {
    private static final List<? extends Number> SCORES = Arrays.asList(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0);

    private final VerificationService verificationService;

    /**
     * Instantiates a new Verification web facade.
     *
     * @param verificationService the verification service
     */
    public VerificationWebFacadeImpl(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    /**
     * Gets verification info by verification id and user role.
     *
     * @param verificationId the verification id
     * @param request        the request
     * @param userRole       the user role
     * @return the verification info by verification id and user role
     * @throws ServiceException the service exception
     */
    @Override
    public Router getVerificationInfoByVerificationIdAndUserRole(long verificationId, HttpServletRequest request, UserRole userRole) throws ServiceException {
        Router router = new Router();
        Optional<Verification> optionalVerification = verificationService.findVerificationById(verificationId);
        if (optionalVerification.isPresent()) {
            request.setAttribute(VERIFICATION_PARAM, optionalVerification.get());
            request.setAttribute(SCORES_PARAM, SCORES);
            switch (userRole) {
                case TRAINER -> router.setPagePath(VERIFICATION_TRAINER_CONTROL);
                case EXAMINER -> router.setPagePath(VERIFICATION_EXAMINER_CONTROL);
                default -> router.setPagePath(VERIFICATION_INFO);
            }
        } else {
            router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
        }

        return router;
    }

    /**
     * Gets verification id by student id.
     *
     * @param userId the user id
     * @return the verification id by student id
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Long> getVerificationIdByStudentId(long userId) throws ServiceException {
        return verificationService.findVerificationByStudentId(userId).map(Verification::getId);
    }
}


