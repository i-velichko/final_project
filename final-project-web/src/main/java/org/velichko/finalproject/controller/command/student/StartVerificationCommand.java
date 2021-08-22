package org.velichko.finalproject.controller.command.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.MessageNameKey;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

public class StartVerificationCommand implements Command {
    private static final String MESSAGE_TO_TRAINER = "A new project has appeared for verification. Detailed information can be found in your personal account. " +
            "<a href=http://localhost:8080/final_project_web_war_exploded/controller?command=show_all_check_waiting_projects>link</a>";
    private final UserService userService;
    private final VerificationService verificationService;
    private final I18nManager i18n;
    private final EmailService emailService;
    private final BaseDataValidator verificationDataValidator;

    public StartVerificationCommand(UserService userService, VerificationService verificationService,
                                    I18nManager i18n, EmailService emailService, BaseDataValidator verificationDataValidator) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.i18n = i18n;
        this.emailService = emailService;
        this.verificationDataValidator = verificationDataValidator;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);

        String gitLink = request.getParameter(GIT_LINK_PARAM);
        String projectTitle = request.getParameter(PROJECT_TITLE_PARAM);
        String trainerId = request.getParameter(TRAINER_ID_PARAM);

        Map<String, String> startVerificationData = new HashMap<>();
        startVerificationData.put(GIT_LINK_PARAM, gitLink);
        startVerificationData.put(PROJECT_TITLE_PARAM, projectTitle);

        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {

            Optional<User> optionalTrainer = Optional.empty();
            try {
                if (trainerId != null) {
                    optionalTrainer = userService.findUserById(Long.parseLong(trainerId));
                }
                User student = (User) request.getSession().getAttribute(USER_PARAM);
                String login = null;
                if (student != null) {
                    login = student.getLogin();
                } else {
                    router.setPagePath(LOGIN_PAGE);
                }

                Part image = request.getPart(IMAGE_PARAM);
                InputStream inputStream = image.getInputStream();
                userService.changeUserImage(login, inputStream);
//            } catch (IOException | ServletException | ServiceException e) {
//                request.setAttribute(UPLOAD_IMAGE_ERROR_PARAM, i18n.getMassage(MessageNameKey.IMAGE_NOT_UPLOAD_KEY, locale));
//            }

                Map<String, String> verificationDataCheckResult = verificationDataValidator.checkValues(startVerificationData, locale);

                if (!verificationDataCheckResult.isEmpty()) {
                    request.setAttribute(CORRECT_VERIFICATION_DATA_PARAM, startVerificationData);
                    request.setAttribute(ERROR_VERIFICATION_DATA_PARAM, verificationDataCheckResult);
                    router.setPagePath(WELCOME_STUDENT);
                } else {
                    userService.changeUserGit(login, gitLink);
                    if (optionalTrainer.isPresent()) {
                        emailService.sendEmail(optionalTrainer.map(User::getEmail).get(), MESSAGE_TO_TRAINER);
                    }
                    Verification verification = new Verification(projectTitle, student, optionalTrainer.get(), VerificationStatus.DRAFT, LocalDateTime.now());
                    //todo подтверждение тренера о начале защиты и смена статуса
                    verificationService.createNewVerification(verification, projectTitle);
                    router.setPagePath(USER_INFO);
                }
            } catch (ServiceException | IOException | ServletException e) {
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        return router;
    }
}
