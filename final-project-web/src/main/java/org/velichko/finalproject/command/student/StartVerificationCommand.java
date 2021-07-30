package org.velichko.finalproject.command.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.controller.Router;
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

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

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
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
//        User trainer = (User) request.getSession().getAttribute(TRAINER_PARAM);
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

//        if (projectName != null) {
//            Verification verification = new Verification(projectName, student, trainer, VerificationStatus.DRAFT, LocalDateTime.now());
//            //todo подтверждение тренера о начале защиты
//            verificationService.createNewVerification(verification, projectName);
//        }
        request.setAttribute(GIT_LINK, gitLink);
        request.setAttribute(PROJECT_NAME_PARAM, projectName);

        router.setPagePath(STUDENT_INFO);
//        router.setPagePath(PageName.VERIFICATION_INFO);
        return router;
    }
}
