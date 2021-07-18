package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.velichko.finalproject.command.PageName.LOGIN_PAGE;
import static org.velichko.finalproject.command.PageName.REGISTRATION;
import static org.velichko.finalproject.command.ParamName.*;
import static org.velichko.finalproject.validator.DataUserValidator.*;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl();
    private final I18nManager i18n = I18nManager.getInstance();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {

        User user = new User();
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);

        Map<String, Boolean> dataCheckService = new HashMap<>();
        dataCheckService.put(login, false);
        dataCheckService.put(password, false);
        dataCheckService.put(confirmPassword, false);

        if (login != null && login.matches(LOGIN.getRegExp())) {
            try {
                if (service.isLoginUnique(login)) {
                    dataCheckService.put(login, true);
                } else {
                    request.setAttribute(LOGIN_ERROR_PARAM, i18n.getMassage("error.login.not.unique", request.getLocale()));
                }
            } catch (ServiceException e) {
                logger.log(Level.DEBUG, "Login is not unique " + login);
            }
            user.setLogin(login);
        } else {
            request.setAttribute(LOGIN_ERROR_PARAM, LOGIN.getMessage());
        }

        if (email != null && email.matches(EMAIL.getRegExp())) {
            try {
                if (service.isEmailUnique(email)) {
                    dataCheckService.put(email, true);
                } else {
                    request.setAttribute(EMAIL_ERROR_PARAM, "Email is not unique!");
                }
            } catch (ServiceException e) {
                logger.log(Level.DEBUG, "Email is not unique " + email);
            }
            user.setEmail(email);
        } else {
            request.setAttribute(EMAIL_ERROR_PARAM, EMAIL.getMessage());
        }

        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }

        user.setRole(UserRole.STUDENT);
        user.setStatus(UserStatus.ACTIVE);

        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (password.equals(confirmPassword)) {
                dataCheckService.put(password, true);
            } else {
                logger.log(Level.DEBUG, "The password is incorrect or the passwords do not match ");
                request.setAttribute(PASSWORD_ERROR_PARAM, CHECK_PASSWORD.getMessage());
            }
        } else {
            request.setAttribute(PASSWORD_ERROR_PARAM, PASSWORD.getMessage());
        }

        if (!dataCheckService.containsValue(false)) {
            try {
                service.createNewUser(user, password);
                request.setAttribute(ParamName.REGISTRATION_IS_DONE, "Registration completed successfully. You can login");
                router.setPagePath(LOGIN_PAGE);
                request.setAttribute(USER_PARAM, user);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error with create new user.", e);
            }
        } else {
            router.setPagePath(REGISTRATION);
        }


        //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли


        return router;
    }
}
