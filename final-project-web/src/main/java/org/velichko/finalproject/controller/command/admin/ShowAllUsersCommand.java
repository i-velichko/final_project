package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.Page;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.List;

import static org.velichko.finalproject.controller.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.controller.command.PageName.SHOW_ALL_USERS;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.logic.dao.BaseDao.PAGE_SIZE;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }



    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            int pageToDisplay = getPage(request);
            List<User> users = userService.readByPage(pageToDisplay);
            int userCount = userService.getUserCount();
            request.setAttribute(USER_LIST_PARAM, users);
            request.setAttribute(PAGEABLE, new Page(userCount, pageToDisplay, PAGE_SIZE));
            router.setPagePath(SHOW_ALL_USERS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error with loading users from db", e); //todo
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
