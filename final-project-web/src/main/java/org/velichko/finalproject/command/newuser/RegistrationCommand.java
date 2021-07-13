package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.validator.DataUserValidator;

import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl(); //todo сделать синглтон
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {

        User user = new User();
        String firstName = request.getParameter(ParamName.FIRST_NAME_PARAM);
        String lastName = request.getParameter(ParamName.LAST_NAME_PARAM);
        String email = request.getParameter(ParamName.EMAIL_PARAM);
        String login = request.getParameter(ParamName.LOGIN_PARAM);
        String password = request.getParameter(ParamName.PASSWORD_PARAM);
        String confirmPassword = request.getParameter(ParamName.CONFIRM_PASSWORD_PARAM);

        Map<String, Boolean> dataCheckService = new HashMap<>();
        dataCheckService.put(login, false);
        dataCheckService.put(password, false);
        dataCheckService.put(confirmPassword, false);

        if (login.matches(DataUserValidator.LOGIN.getRegExp())) {
            //todo проверка на то есть ли такой в базе, если есть такой то тоже оповестить
            dataCheckService.put(login, true);
            user.setLogin(login);
        } else {
            request.setAttribute(ParamName.LOGIN_ERROR_PARAM, DataUserValidator.LOGIN.getMessage());
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        if (email.matches(DataUserValidator.EMAIL.getRegExp())) {
            dataCheckService.put(email, true);
            user.setEmail(email);
        } else {
            request.setAttribute(ParamName.EMAIL_ERROR_PARAM, DataUserValidator.EMAIL.getMessage());
        }

        user.setRole(UserRole.STUDENT);
        user.setStatus(UserStatus.ACTIVE);

        if (password.matches(DataUserValidator.PASSWORD.getRegExp())) {
            dataCheckService.put(password, true);
        } else {
            request.setAttribute(ParamName.PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
        }
        if (password.equals(confirmPassword)) {
            try {
                service.createNewUser(user, password);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while client registration data", e);
//                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR)); //todo Error page
            }
        } else {
            request.setAttribute(ParamName.PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
        }

        //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли

        router.setPagePath(PageName.REGISTRATION);
        request.setAttribute(ParamName.USER_PARAM, user);
        router.setPagePath(PageName.LOGIN_PAGE); //todo убрать и продумать всю логику неудачной регистрации
        return router;
    }
}
