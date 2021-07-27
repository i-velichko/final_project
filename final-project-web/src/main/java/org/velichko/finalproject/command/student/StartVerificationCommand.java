package org.velichko.finalproject.command.student;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;

import static org.velichko.finalproject.command.ParamName.*;

public class StartVerificationCommand implements Command {
    EmailService emailService = new EmailServiceImpl();
    Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {

        String gitLink = request.getParameter(GIT_LINK);
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String studentSkills = request.getParameter(STUDENT_SKILLS_PARAM);

        if (projectName != null) {
            try {
                //todo
                emailService.sendEmail("showman.velichko@gmail.com", "http://localhost:8080/final_project_web_war_exploded/controller?command=start_verification" );
            } catch (ServiceException e) {
                e.printStackTrace(); //todo
            }
        }
        request.setAttribute(GIT_LINK, gitLink);
        request.setAttribute(PROJECT_NAME_PARAM, projectName);
        request.setAttribute(STUDENT_SKILLS_PARAM, studentSkills);

        router.setPagePath(PageName.STUDENT_INFO);
//        router.setPagePath(PageName.VERIFICATION_INFO);
        return router;
    }
}
