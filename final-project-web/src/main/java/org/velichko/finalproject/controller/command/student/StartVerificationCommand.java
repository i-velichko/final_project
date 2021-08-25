package org.velichko.finalproject.controller.command.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
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

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.logic.entity.type.VerificationStatus.DRAFT;

/**
 * @author Ivan Velichko
 *
 * The type Start verification command.
 */
public class StartVerificationCommand implements Command {
    private static final String MESSAGE_TO_TRAINER = "A new project has appeared for verification. Detailed information can be found in your personal account. " +
            "<a href=http://localhost:8080/final_project_web_war_exploded/controller?command=show_all_check_waiting_projects>link</a>";
    private static final String USER_ID_TRANSFER_PARAM = "&userId=";
    private final UserService userService;
    private final VerificationService verificationService;
    private final EmailService emailService;
    private final BaseDataValidator verificationDataValidator;

    /**
     * Instantiates a new Start verification command.
     *
     * @param userService               the user service
     * @param verificationService       the verification service
     * @param emailService              the email service
     * @param verificationDataValidator the verification data validator
     */
    public StartVerificationCommand(UserService userService, VerificationService verificationService,
                                    EmailService emailService, BaseDataValidator verificationDataValidator) {
        this.userService = userService;
        this.verificationService = verificationService;
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
            User student = (User) request.getSession().getAttribute(USER_PARAM);
            try {
                if (trainerId != null && student != null) {
                    Optional<User> optionalTrainer = userService.findUserById(Long.parseLong(trainerId));
                    String login = student.getLogin();
                    Part image = request.getPart(IMAGE_PARAM);
                    InputStream inputStream = image.getInputStream();
                    userService.changeUserImage(login, inputStream);

                    Map<String, String> errors = verificationDataValidator.checkValues(startVerificationData, locale);

                    if (errors.isEmpty() && optionalTrainer.isPresent()) {
                        userService.changeUserGit(login, gitLink);
                        emailService.sendEmail(optionalTrainer.get().getEmail(), MESSAGE_TO_TRAINER);
                        Verification verification = new Verification(projectTitle, student, optionalTrainer.get(), DRAFT, LocalDateTime.now());
                        verificationService.createNewVerification(verification, projectTitle);
                        router.setRouterType(REDIRECT);
                        router.setPagePath(REDIRECT_USER_INFO + USER_ID_TRANSFER_PARAM + student.getId());
                    } else {
                        request.setAttribute(CORRECT_VERIFICATION_DATA_PARAM, startVerificationData);
                        request.setAttribute(ERROR_VERIFICATION_DATA_PARAM, errors);
                        router.setPagePath(WELCOME_STUDENT);
                    }
                } else {
                    router.setPagePath(LOGIN_PAGE);
                }
            } catch (ServiceException | IOException | ServletException e) {
                LOGGER.log(Level.ERROR, "error with create verification", e);
                request.setAttribute(ERROR_MESSAGE, e.getMessage());
                router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
            }
        }
        return router;
    }
}
