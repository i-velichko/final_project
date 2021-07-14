package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

public class ShowTrainerInfoCommand implements Command {
    private final UserService service = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = null;
        Optional<User> currentUser;

        String login = request.getParameter(ParamName.LOGIN_PARAM);
        String password = request.getParameter(ParamName.PASSWORD_PARAM);

        try {
            currentUser = service.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.setAttribute("user", user);
                router.setPagePath(PageName.TRAINER_INFO);
            }

        } catch (ServiceException e) {
            e.printStackTrace(); //todo
        }



        return router;
    }
}
