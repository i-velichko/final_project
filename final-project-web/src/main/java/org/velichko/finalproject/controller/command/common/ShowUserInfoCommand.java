package org.velichko.finalproject.controller.command.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.controller.command.PageName.USER_INFO;
import static org.velichko.finalproject.controller.command.ParamName.*;
import static org.velichko.finalproject.controller.command.ParamName.USER_PARAM;

public class ShowUserInfoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SRC = "data:image/jpeg;base64,";
    private final UserService userService;

    public ShowUserInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        try {
            Optional<User> currentUser = userService.findUserById(Long.parseLong(userId));
            if (currentUser.isPresent()) {
                User user = currentUser.get();
                if (user.getImage() != null) {
                    byte[] byteImage = user.getImage().getBinaryStream().readAllBytes();
                    byte[] encodeBase64 = Base64.getEncoder().encode(byteImage);
                    String base64DataString = new String(encodeBase64, StandardCharsets.UTF_8);
                    String source = SRC + base64DataString;
                    request.setAttribute(STRING_IMAGE_PARAM, source);
                }
                request.setAttribute(USER_PARAM, user);
                router.setPagePath(USER_INFO);
            }
        } catch (ServiceException | SQLException | IOException e) {
            LOGGER.log(Level.ERROR, "Error with download user page with this user: " + userId);
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
