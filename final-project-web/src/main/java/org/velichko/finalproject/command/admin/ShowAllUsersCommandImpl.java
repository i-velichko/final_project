package org.velichko.finalproject.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.List;

public class ShowAllUsersCommandImpl implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        UserService service = new UserServiceImpl();
        List<User> users = service.readAll();
        request.setAttribute(ParamConstant.USER_LIST_PARAM, users);
        router.setPagePath(PageConstant.SHOW_ALL_USERS);
        return router;
    }
}
