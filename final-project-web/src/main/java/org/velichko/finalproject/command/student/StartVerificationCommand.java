package org.velichko.finalproject.command.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.*;
import static org.velichko.finalproject.command.ParamName.*;

public class StartVerificationCommand implements Command {
    EmailService emailService = new EmailServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final VerificationService verificationService = new VerificationServiceImpl();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {

        String gitLink = request.getParameter(GIT_LINK);
        String title = request.getParameter(PROJECT_TITLE_PARAM);
        String trainerId = request.getParameter("trainerId");
        User student = (User) request.getSession().getAttribute(USER_PARAM);
        String login = null;
        if (student != null) {
            login = student.getLogin();
        } else {
            router.setPagePath(ERROR_PAGE);
        }

        try {
            Part image = request.getPart("image");
            InputStream inputStream = image.getInputStream();
            userService.changeUserImage(login, inputStream);
        } catch (IOException | ServletException | ServiceException e) {
            e.printStackTrace();//todo
        }
        if (gitLink != null) {
            try {
                userService.changeUserGit(login, gitLink);
                emailService.sendEmail("showman.velichko@gmail.com", "http://localhost:8080/final_project_web_war_exploded/controller?command=start_verification");
            } catch (ServiceException e) {
                e.printStackTrace(); //todo
            }
        } else {
            //todo ошибка на страницу что надо заполнить гит
        }

        if (title != null) {
            User trainer = null;
            Optional<User> optionalTrainer = Optional.empty();
            try {
                optionalTrainer = userService.findUserById(Long.parseLong(trainerId));
            } catch (ServiceException e) {
                router.setPagePath(ERROR_PAGE);
            }
            if (optionalTrainer.isPresent()) {
                trainer = optionalTrainer.get();
            }
            Verification verification = new Verification(title, student, trainer, VerificationStatus.DRAFT, LocalDateTime.now());
            //todo подтверждение тренера о начале защиты
            try {
                verificationService.createNewVerification(verification, title);
            } catch (ServiceException e) {
                router.setPagePath(ERROR_PAGE);
            }
        }
        router.setPagePath(STUDENT_INFO);
//        router.setPagePath(PageName.VERIFICATION_INFO);
        return router;
    }
}
