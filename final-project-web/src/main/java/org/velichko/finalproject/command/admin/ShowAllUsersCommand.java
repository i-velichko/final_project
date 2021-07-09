package org.velichko.finalproject.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.List;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService service = new UserServiceImpl();
        List<User> users = null;
        try {
            users = service.readAll();
            request.setAttribute(ParamConstant.USER_LIST_PARAM, users);
            router.setPagePath(PageConstant.SHOW_ALL_USERS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"Error while client registration data", e);
//            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR)); //todo Error page
        }
        return router;
    }
}
