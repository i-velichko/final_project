package org.velichko.finalproject.controller.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.AdmissionScoreCheckService;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;

import static org.velichko.finalproject.logic.entity.type.VerificationStatus.WAIT_FOR_EXAMINER_CHECK;

public class ChangeTrainerScoreCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;
    private final UserService userService;
    private final EmailService emailService;
    private final AdmissionScoreCheckService admissionScoreCheckService;

    public ChangeTrainerScoreCommand(VerificationService verificationService, UserService userService, EmailService emailService, AdmissionScoreCheckService admissionScoreCheckService) {
        this.verificationService = verificationService;
        this.userService = userService;
        this.emailService = emailService;
        this.admissionScoreCheckService = admissionScoreCheckService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Long verificationId = Long.parseLong(request.getParameter(ParamName.VERIFICATION_ID_PARAM));
        String newScore = request.getParameter(ParamName.NEW_SCORE_PARAM);
        Double studentScore = Double.parseDouble(newScore);
        if (admissionScoreCheckService.checkScore(studentScore)) {
            try {
                verificationService.changeVerificationStatusById(verificationId, WAIT_FOR_EXAMINER_CHECK);
            } catch (ServiceException e) {
                router.setPagePath(PageName.ERROR_PAGE);
            }
        } else {
            //todo status на гамон и скорбное письмо
        }

        if (studentScore >= 5) {

            //todo письмо рандомному экзаминатору
        }

        try {
            verificationService.changeTrainerScore(verificationId, studentScore);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change new score by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(PageName.REFERER));
        return router;
    }
}