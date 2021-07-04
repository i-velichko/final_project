package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
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

        if (DataUserValidator.isLoginValid(login)) {
            dataCheckService.put(login, true);
            user.setLogin(login);
        }else {
            request.setAttribute(ParamConstant.LOGIN_PARAM, "Login is not correct, please, try again!");
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        if (DataUserValidator.isEmailValid(email)){
            dataCheckService.put(email, true);
            user.setEmail(email);
        }else {
            request.setAttribute(ParamConstant.EMAIL_PARAM, "Email is not correct, please, try again.");
        }

        user.setRole(UserRole.STUDENT);
        user.setStatus(UserStatus.ACTIVE);

        if (DataUserValidator.isPasswordValid(password)){
            dataCheckService.put(password, true);
        }else {
            request.setAttribute(ParamConstant.PASSWORD_PARAM, "Password is not correct, please, try again. ");
        }
        if (password.equals(confirmPassword)){
            try {
                service.createNewUser(user, password);
            } catch (ServiceException e) {
                logger.error("Error while client registration data", e);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR)); //todo
            }
        }else {
            request.setAttribute(ParamConstant.PASSWORD_PARAM, "Passwords are differ");
        }

        //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли

        router.setPagePath(PageConstant.REGISTRATION);
        request.setAttribute(ParamConstant.USER_PARAM, user);
        router.setPagePath(PageConstant.INDEX_PAGE);
        return router;
    }
}
