package org.velichko.finalproject.command.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.MessageNameKey;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;
import org.velichko.finalproject.validator.VerificationDataValidator;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.*;
import static org.velichko.finalproject.command.ParamName.*;

public class StartVerificationCommand implements Command {
    private static final String SEND_EMAIL_ADDRESS = "http://localhost:8080/final_project_web_war_exploded/controller?command=start_verification";
    private final UserService userService = new UserServiceImpl();
    private final VerificationService verificationService = new VerificationServiceImpl();
    private final I18nManager i18n = I18nManager.getInstance();
    EmailService emailService = new EmailServiceImpl();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);

        String gitLink = request.getParameter(GIT_LINK);
        String projectTitle = request.getParameter(PROJECT_TITLE_PARAM);
        String trainerId = request.getParameter(TRAINER_ID_PARAM);
        String trainerEmail = null;

        Map<String, String> startVerificationData = new HashMap<>();
        startVerificationData.put(GIT_LINK, gitLink);
        startVerificationData.put(PROJECT_TITLE_PARAM, projectTitle);

        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {

            User trainer = null;
            Optional<User> optionalTrainer = Optional.empty();
            try {
                if (trainerId != null) {
                    optionalTrainer = userService.findUserById(Long.parseLong(trainerId));
                }
            } catch (ServiceException e) {
                router.setPagePath(ERROR_PAGE);
            }
            if (optionalTrainer.isPresent()) {
                trainer = optionalTrainer.get();
                trainerEmail = trainer.getEmail();
            }

            User student = (User) request.getSession().getAttribute(USER_PARAM);
            String login = null;
            if (student != null) {
                login = student.getLogin();
            } else {
                router.setPagePath(LOGIN_PAGE);
            }

            try {
                Part image = request.getPart(IMAGE_PARAM);
                InputStream inputStream = image.getInputStream();
                userService.changeUserImage(login, inputStream);
            } catch (IOException | ServletException | ServiceException e) {
                request.setAttribute(UPLOAD_IMAGE_ERROR_PARAM, i18n.getMassage(MessageNameKey.IMAGE_NOT_UPLOAD_KEY, locale));
            }

            Map<String, String> verificationDataCheckResult = VerificationDataValidator.checkValues(startVerificationData, locale);

            if (!verificationDataCheckResult.isEmpty()) {
                request.setAttribute(CORRECT_VERIFICATION_DATA_PARAM, startVerificationData);
                request.setAttribute(ERROR_VERIFICATION_DATA_PARAM, verificationDataCheckResult);
                router.setPagePath(WELCOME_STUDENT);
            } else {
                try {
                    userService.changeUserGit(login, gitLink);
                    if (trainerEmail != null) {
                        emailService.sendEmail(trainerEmail, SEND_EMAIL_ADDRESS);
                    }
                } catch (ServiceException e) {
                    router.setPagePath(ERROR_PAGE);
                }
                Verification verification = new Verification(projectTitle, student, trainer, VerificationStatus.DRAFT, LocalDateTime.now());
                //todo подтверждение тренера о начале защиты
                try {
                    verificationService.createNewVerification(verification, projectTitle);
                } catch (ServiceException e) {
                    router.setPagePath(ERROR_PAGE);
                }
                router.setPagePath(STUDENT_INFO);
//        router.setPagePath(PageName.VERIFICATION_INFO);
            }
        }
        return router;
    }
}
