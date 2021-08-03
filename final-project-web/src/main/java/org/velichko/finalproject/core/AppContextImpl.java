package org.velichko.finalproject.core;

import org.velichko.finalproject.command.common.LoginCommand;
import org.velichko.finalproject.command.newuser.RegistrationCommand;
import org.velichko.finalproject.command.student.StartVerificationCommand;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;
import org.velichko.finalproject.logic.utill.RegistrationConfirmatory;
import org.velichko.finalproject.validator.RegistrationDataValidator;

import java.util.HashMap;
import java.util.Map;

public class AppContextImpl implements AppContext {

    private Map<Class, Object> map = new HashMap<>();

    private AppContextImpl() {
        map.put(UserService.class, UserServiceImpl.getInstance());
        map.put(VerificationService.class, VerificationServiceImpl.getInstance());
        map.put(RegistrationDataValidator.class, RegistrationDataValidator.getInstance());
        map.put(I18nManager.class, new I18nManager());
        map.put(EmailService.class, new EmailServiceImpl());
        map.put(RegistrationConfirmatory.class, new RegistrationConfirmatory(getService(EmailService.class)));
        map.put(RegistrationCommand.class, new RegistrationCommand(
                getService(UserService.class),
                getService(RegistrationConfirmatory.class),
                getService(RegistrationDataValidator.class),
                getService(I18nManager.class)
        ));
        map.put(StartVerificationCommand.class, new StartVerificationCommand(
                getService(UserService.class),
                getService(VerificationService.class),
                getService(I18nManager.class),
                getService(EmailService.class)
        ));
        map.put(LoginCommand.class, new LoginCommand(getService(UserService.class)));
    }

    @Override
    public <T> T getService(Class<T> tClass) {
        return (T) map.get(tClass);
    }

    private static class AppContextImplHolder {
        public static final AppContext HOLDER_INSTANCE = new AppContextImpl();
    }

    public static AppContext getInstance() {
        return AppContextImpl.AppContextImplHolder.HOLDER_INSTANCE;
    }

}
