package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageConstant;
import org.velichko.finalproject.command.ParamConstant;
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
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserService service = new UserServiceImpl();
        User user = new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        Map<String, Boolean> dataCheckService = new HashMap<>();
        dataCheckService.put(login, false);
        dataCheckService.put(password, false);
        dataCheckService.put(confirmPassword, false);

        if (login.matches(DataUserValidator.LOGIN.getRegExp())) {
            dataCheckService.put(login, true);
            user.setLogin(login);
        } else {
            request.setAttribute(ParamConstant.LOGIN_ERROR_PARAM, DataUserValidator.LOGIN.getMessage());
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        if (email.matches(DataUserValidator.EMAIL.getRegExp())) {
            dataCheckService.put(email, true);
            user.setEmail(email);
        } else {
            request.setAttribute(ParamConstant.EMAIL_ERROR_PARAM, DataUserValidator.EMAIL.getMessage());
        }

        user.setRole(UserRole.STUDENT);
        user.setStatus(UserStatus.ACTIVE);

        if (password.matches(DataUserValidator.PASSWORD.getRegExp())) {
            dataCheckService.put(password, true);
        } else {
            request.setAttribute(ParamConstant.PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
        }
        if (password.equals(confirmPassword)) {
            try {
                service.createNewUser(user, password);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while client registration data", e);
//                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR)); //todo Error page
            }
        } else {
            request.setAttribute(ParamConstant.PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
        }

        //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли

        router.setPagePath(PageConstant.REGISTRATION);
        request.setAttribute(ParamConstant.USER_PARAM, user);
        router.setPagePath(PageConstant.INDEX_PAGE);
        return router;
    }
}
