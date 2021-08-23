package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.io.IOException;
import java.io.InputStream;

import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * @author Ivan Velichko
 *
 * The type Change user image command.
 */
public class ChangeUserImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService;

    /**
     * Instantiates a new Change user image command.
     *
     * @param userService the user service
     */
    public ChangeUserImageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = request.getParameter(ParamName.LOGIN_PARAM);
        try {
            Part image = request.getPart(IMAGE_PARAM);
            InputStream inputStream = image.getInputStream();
            userService.changeUserImage(login, inputStream);
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.log(Level.ERROR, "Error with upload image, try again", e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(REFERER));
        return router;
    }
}
