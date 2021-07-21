package org.velichko.finalproject.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.validator.RegistrationDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.command.MessageNameKey.REGISTRATION_SUCCESSFUL_KEY;
import static org.velichko.finalproject.command.PageName.LOGIN_PAGE;
import static org.velichko.finalproject.command.ParamName.*;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService service = new UserServiceImpl();
    private final I18nManager i18n = I18nManager.getInstance();
    Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {

        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);

        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);

        Map<String, String> registrationData = new HashMap<>();
        registrationData.put(FIRST_NAME_PARAM, firstName);
        registrationData.put(LAST_NAME_PARAM, lastName);
        registrationData.put(EMAIL_PARAM, email);
        registrationData.put(LOGIN_PARAM, login);
        registrationData.put(PASSWORD_PARAM, password);
        registrationData.put(CONFIRM_PASSWORD_PARAM, confirmPassword);

        String method = request.getMethod();
        if (method.equals("POST")) {
            Map<String, String> registrationDataCheckResult = RegistrationDataValidator.checkValues(registrationData, locale);
            if (!registrationDataCheckResult.isEmpty()) {
                request.setAttribute("correctRegistrationData", registrationData);
                request.setAttribute("errorRegistrationData", registrationDataCheckResult);
                router.setPagePath(PageName.REGISTRATION);
            } else {
                User user = new User(firstName, lastName, login, email, UserRole.STUDENT, UserStatus.ACTIVE);
                System.out.println(user);
                try {
                    service.createNewUser(user, password);
                } catch (ServiceException e) {
                    e.printStackTrace(); //todo
                }
                request.setAttribute(USER_PARAM, user);
                request.setAttribute(ParamName.REGISTRATION_IS_DONE, i18n.getMassage(REGISTRATION_SUCCESSFUL_KEY, locale));
                router.setPagePath(LOGIN_PAGE);
            }
        }
        return router;
    }
}
//todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли


