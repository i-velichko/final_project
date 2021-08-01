package org.velichko.finalproject.command.trainer;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.ERROR_PAGE;
import static org.velichko.finalproject.command.PageName.STUDENT_INFO;
import static org.velichko.finalproject.command.ParamName.USER_PARAM;

public class ShowStudentInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        User user;
        Optional<User> currentUser;
        try {
            currentUser = service.findUserById(Long.parseLong(userId));
            if (currentUser.isPresent()) {
                user = currentUser.get();

                byte[] byteImage = user.getImage().getBinaryStream().readAllBytes();
                if (byteImage != null) {
                    byte[] encodeBase64 = Base64.getEncoder().encode(byteImage);
                    String base64DataString = new String(encodeBase64, StandardCharsets.UTF_8);
                    String src = "data:image/jpeg;base64," + base64DataString;
                    request.setAttribute("stringImage", src);
                } else {
                    //todo отправить картинку с надписью photo
                }
                request.setAttribute(USER_PARAM, user);
                router.setPagePath(STUDENT_INFO);
            }
        } catch (ServiceException | SQLException | IOException e) {
            logger.log(Level.ERROR, "Error with download user page with this user: " + userId);
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
