package org.velichko.finalproject.controller.command.student;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.webfacade.VerificationWebFacade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.velichko.finalproject.controller.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.controller.command.PageName.WELCOME_STUDENT;
import static org.velichko.finalproject.controller.command.ParamName.TRAINER_LIST_PARAM;
import static org.velichko.finalproject.controller.command.ParamName.USER_PARAM;

public class WelcomeStudentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final VerificationWebFacade verificationWebFacade;

    public WelcomeStudentCommand(UserService userService, VerificationWebFacade verificationWebFacade) {
        this.userService = userService;
        this.verificationWebFacade = verificationWebFacade;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        User currentUser = (User) request.getSession().getAttribute(USER_PARAM);
        request.setAttribute(USER_PARAM, currentUser);
        try {
            Optional<Long> verificationIdByStudentId = verificationWebFacade.getVerificationIdByStudentId(currentUser.getId());
            if (verificationIdByStudentId.isPresent()) {
                return verificationWebFacade.getVerificationInfoByVerificationIdAndUserRole(
                        verificationIdByStudentId.get(), request, currentUser.getRole());
            } else {
                List<User> users = userService.readAll();
                List<User> trainers = users.stream()
                        .filter(user -> user.getRole().equals(UserRole.TRAINER))
                        .collect(Collectors.toList());
                request.setAttribute(TRAINER_LIST_PARAM, trainers);

                router.setPagePath(WELCOME_STUDENT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while client registration data", e); //todo
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
