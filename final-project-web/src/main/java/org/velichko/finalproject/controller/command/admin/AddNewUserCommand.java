package org.velichko.finalproject.controller.command.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.Router.RouterType.REDIRECT;
import static org.velichko.finalproject.controller.command.MessageNameKey.REGISTRATION_FAILED_KEY;
import static org.velichko.finalproject.controller.command.PageName.ADD_USER_PAGE;
import static org.velichko.finalproject.controller.command.PageName.REDIRECT_TO_ADD_USER_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.*;

/**
 * author Ivan Velichko
 * .
 * The type Add new user command.
 */
public class AddNewUserCommand implements Command {
    private final UserService userService;
    private final BaseDataValidator registrationDataValidator;
    private final I18nManager i18n;


    /**
     * Instantiates a new Add new user command.
     *
     * @param userService               the user service
     * @param registrationDataValidator the registration data validator
     * @param i18n                      the 18 n
     */
    public AddNewUserCommand(UserService userService, BaseDataValidator registrationDataValidator, I18nManager i18n) {
        this.userService = userService;
        this.registrationDataValidator = registrationDataValidator;
        this.i18n = i18n;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String role = request.getParameter(ROLE_PARAM);
        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);

        Map<String, String> registrationData = new HashMap<>();
        registrationData.put(FIRST_NAME_PARAM, firstName);
        registrationData.put(LAST_NAME_PARAM, lastName);
        registrationData.put(LOGIN_PARAM, login);
        registrationData.put(PASSWORD_PARAM, password);
        registrationData.put(EMAIL_PARAM, email);
        registrationData.put(ROLE_PARAM, role);

        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {
            Map<String, String> registrationDataCheckResult = registrationDataValidator.checkValues(registrationData, locale);
            try {
                if (!registrationDataCheckResult.isEmpty()) {
                    request.setAttribute(CORRECT_REGISTRATION_DATA_PARAM, registrationData);
                    request.setAttribute(ERROR_REGISTRATION_DATA_PARAM, registrationDataCheckResult);
                    router.setPagePath(ADD_USER_PAGE);
                } else {
                    UserRole userRole = UserRole.valueOf(role);
                    User user = new User(firstName, lastName, login, email, userRole, UserStatus.ACTIVE);
                    if (userService.createNewUser(user, password)) {
                        router.setRouterType(REDIRECT);
                        router.setPagePath(REDIRECT_TO_ADD_USER_PAGE);
                    }
                }
            } catch (ServiceException | IllegalArgumentException e) {
                logger.log(Level.ERROR, "Error user creating", e);
                e.printStackTrace(); //todo
                request.setAttribute(REGISTRATION_FAILED, i18n.getMassage(REGISTRATION_FAILED_KEY, locale) + e.getLocalizedMessage());
            }
        } else {
            router.setPagePath(ADD_USER_PAGE);
        }
        return router;
    }
}
