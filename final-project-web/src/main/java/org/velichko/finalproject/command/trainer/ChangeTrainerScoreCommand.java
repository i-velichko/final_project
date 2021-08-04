package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class ChangeTrainerScoreCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final VerificationService verificationService;
    private final UserService userService;
    private final EmailService emailService;

    public ChangeTrainerScoreCommand(VerificationService verificationService, UserService userService, EmailService emailService) {
        this.verificationService = verificationService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Long verificationId = Long.parseLong(request.getParameter(VERIFICATION_ID_PARAM));
        String newScore = request.getParameter(NEW_SCORE_PARAM);
        Double score = Double.parseDouble(newScore);
        if (score >= 5) {  //todo читать средний юалл допуска из пропертей
            //todo статус на вейт экзаминатор
            //todo письмо рандомному экзаминатору
        }

        try {
            verificationService.changeTrainerScore(verificationId, score);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change new score by this " + verificationId + " verification");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
