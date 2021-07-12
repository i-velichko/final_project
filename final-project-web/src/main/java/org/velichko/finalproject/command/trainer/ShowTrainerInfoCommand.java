package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.Optional;

public class ShowTrainerInfoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserServiceImpl userService = new UserServiceImpl();
        User user = null;
        Optional<User> currentUser;

        String login = request.getParameter(ParamConstant.LOGIN_PARAM);
        String password = request.getParameter(ParamConstant.PASSWORD_PARAM);

        try {
            currentUser = userService.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
                request.setAttribute("user", user);
                router.setPagePath(PageConstant.TRAINER_INFO);
//                request.getRequestDispatcher("/pages/trainer_info.jsp").forward(request);
            }

        } catch (ServiceException e) {
            e.printStackTrace(); //todo
        }



        return router;
    }
}
