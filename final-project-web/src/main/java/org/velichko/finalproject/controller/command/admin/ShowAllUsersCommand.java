package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.Page;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.List;

import static org.velichko.finalproject.controller.command.PageName.SHOW_ALL_USERS;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.logic.dao.BaseDao.PAGE_SIZE;

/**
 * @author Ivan Velichko
 *
 * The type Show all users command.
 */
public class ShowAllUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService;

    /**
     * Instantiates a new Show all users command.
     *
     * @param userService the user service
     */
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
            LOGGER.log(Level.ERROR, "Error with loading users from db", e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
