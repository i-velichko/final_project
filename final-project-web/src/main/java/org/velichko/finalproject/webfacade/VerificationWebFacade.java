package org.velichko.finalproject.webfacade;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.SCORES_PARAM;

public class VerificationWebFacade {
    private static final List<? extends Number> SCORES = Arrays.asList(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0);

    private final VerificationService verificationService;

    public VerificationWebFacade(VerificationService verificationService) {
        this.verificationService = verificationService;
    }


    public Router getVerificationInfoByVerificationIdAndUserRole(long verificationId, HttpServletRequest request, UserRole userRole) throws ServiceException {

        request.setAttribute(SCORES_PARAM, SCORES);
        Router router = new Router();
        Optional<Verification> optionalVerification = verificationService.findVerificationById(verificationId);
        if (optionalVerification.isPresent()) {
            request.setAttribute(ParamName.VERIFICATION_PARAM, optionalVerification.get());
            if (userRole != null) {
                switch (userRole) {
                    case TRAINER -> router.setPagePath(VERIFICATION_TRAINER_CONTROL);
                    case EXAMINER -> router.setPagePath(VERIFICATION_EXAMINER_CONTROL);
                    default -> router.setPagePath(PageName.VERIFICATION_INFO);
                }
            }
            router.setPagePath(PageName.VERIFICATION_INFO);
        } else {
            router.setPagePath(ERROR_PAGE);
        }

        return router;
    }

    public Optional<Long> getVerificationIdByStudentId(long userId) throws ServiceException {
        return verificationService.findVerificationByStudentId(userId).map(Verification::getId);
    }
}


