package org.velichko.finalproject.controller.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.util.RegistrationConfirmatory;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.velichko.finalproject.controller.command.MessageNameKey.REGISTRATION_FAILED_KEY;
import static org.velichko.finalproject.controller.command.PageName.REDIRECT_TO_LOGIN_PAGE;
import static org.velichko.finalproject.controller.command.PageName.REGISTRATION_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.*;


/**
 * @author Ivan Velichko
 * 
 * The type Registration command.
 */
public class RegistrationCommand implements Command {
    
    private final UserService userService;
    private final RegistrationConfirmatory confirmatoryService;
    private final BaseDataValidator registrationDataValidator;

    /**
     * Instantiates a new Registration command.
     *
     * @param userService               the user service
     * @param confirmatoryService       the confirmatory service
     * @param registrationDataValidator the registration data validator
     */
    public RegistrationCommand(UserService userService,
                               RegistrationConfirmatory confirmatoryService,
                               BaseDataValidator registrationDataValidator) {
        this.userService = userService;
        this.confirmatoryService = confirmatoryService;
        this.registrationDataValidator = registrationDataValidator;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

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
        router.setPagePath(REGISTRATION_PAGE);
        if (method.equals(POST_PARAM)) {
            try {
                Map<String, String> errors = registrationDataValidator.checkValues(registrationData, locale);
                if (errors.isEmpty()) {
                    User user = new User(firstName, lastName, login, email, UserRole.STUDENT, UserStatus.WAIT_CONFIRMATION);
                    String registrationKey = confirmatoryService.sendEmailForConfirmRegistration(email, login);
                    if (userService.createNewUser(user, password, registrationKey)) {
                        request.setAttribute(USER_PARAM, user);
                    }
                    router.setRouterType(Router.RouterType.REDIRECT);
                    router.setPagePath(REDIRECT_TO_LOGIN_PAGE + "&" + REGISTRATION_IS_DONE);
                } else {
                    request.setAttribute(CORRECT_REGISTRATION_DATA_PARAM, registrationData);
                    request.setAttribute(ERROR_REGISTRATION_DATA_PARAM, errors);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "error registration user", e);
                request.setAttribute(ERROR_MESSAGE, e.getMessage());
                router.setErrorCode(SC_INTERNAL_SERVER_ERROR);
            }
        }
        return router;
    }
}



