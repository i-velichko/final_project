package org.velichko.finalproject.core;

import org.velichko.finalproject.controller.command.admin.*;
import org.velichko.finalproject.controller.command.common.*;
import org.velichko.finalproject.controller.command.examiner.ChangeExaminerVerificationDateCommand;
import org.velichko.finalproject.controller.command.examiner.ChangeFinalStatusCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationConfirmationCommand;
import org.velichko.finalproject.controller.command.student.StartVerificationCommand;
import org.velichko.finalproject.controller.command.student.WelcomeStudentCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerCharacteristicCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerScoreCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerVerificationDateCommand;
import org.velichko.finalproject.controller.command.trainer.ShowTrainerInfoCommand;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.dao.impl.VerificationDaoImpl;
import org.velichko.finalproject.logic.service.AdmissionScoreCheckService;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.VerificationService;
import org.velichko.finalproject.logic.service.impl.AdmissionScoreCheckServiceImpl;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;
import org.velichko.finalproject.logic.util.RegistrationConfirmatory;
import org.velichko.finalproject.validator.impl.EditUserDataValidator;
import org.velichko.finalproject.validator.impl.RegistrationByAdminValidator;
import org.velichko.finalproject.validator.impl.RegistrationDataValidator;
import org.velichko.finalproject.validator.impl.VerificationDataValidator;
import org.velichko.finalproject.webfacade.VerificationWebFacade;
import org.velichko.finalproject.webfacade.impl.VerificationWebFacadeImpl;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.core.LazyWrapper.buildWrapper;

/**
 * @author Ivan Velichko
 *
 * The type App context.
 */
public class AppContextImpl implements AppContext {
    private final Map<Class<?>, LazyWrapper> map = new HashMap<>();

    private AppContextImpl() {
        map.put(ChangeLocaleCommand.class, buildWrapper(ChangeLocaleCommand::new));
        map.put(ToMainPageCommand.class, buildWrapper(ToMainPageCommand::new));
        map.put(ToRegistrationPageCommand.class, buildWrapper(ToRegistrationPageCommand::new));
        map.put(ToEditUserDataPageCommand.class, buildWrapper(ToEditUserDataPageCommand::new));
        map.put(LogoutCommand.class, buildWrapper(LogoutCommand::new));
        map.put(WrongCommand.class, buildWrapper(WrongCommand::new));
        map.put(UserDao.class, buildWrapper(UserDaoImpl::new));
        map.put(EmailService.class, buildWrapper(EmailServiceImpl::new));
        map.put(AdmissionScoreCheckService.class, buildWrapper(AdmissionScoreCheckServiceImpl::new));
        map.put(VerificationDao.class, buildWrapper(VerificationDaoImpl::new));
        map.put(UserService.class, buildWrapper(() -> new UserServiceImpl(getService(UserDao.class))));
        map.put(VerificationService.class, buildWrapper(() -> new VerificationServiceImpl(getService(VerificationDao.class))));
        map.put(VerificationWebFacade.class, buildWrapper(() -> new VerificationWebFacadeImpl(getService(VerificationService.class))));
        map.put(I18nManager.class, buildWrapper(I18nManager::new));
        map.put(ToAddUserPageCommand.class, buildWrapper(() -> new ToAddUserPageCommand(getService(I18nManager.class))));
        map.put(ToLoginPageCommand.class, buildWrapper(() -> new ToLoginPageCommand(getService(I18nManager.class))));
        map.put(RegistrationDataValidator.class, buildWrapper(() -> new RegistrationDataValidator(
                getService(UserService.class),
                getService(I18nManager.class))));
        map.put(RegistrationByAdminValidator.class, buildWrapper(() -> new RegistrationByAdminValidator(
                getService(UserService.class),
                getService(I18nManager.class))));
        map.put(VerificationDataValidator.class, buildWrapper(() -> new VerificationDataValidator(
                getService(UserService.class),
                getService(I18nManager.class))));
        map.put(EditUserDataValidator.class, buildWrapper(() -> new EditUserDataValidator(
                getService(UserService.class),
                getService(I18nManager.class))));
        map.put(RegistrationConfirmatory.class, buildWrapper(() -> new RegistrationConfirmatory(getService(EmailService.class))));
        map.put(RegistrationCommand.class, buildWrapper(() -> new RegistrationCommand(
                getService(UserService.class),
                getService(RegistrationConfirmatory.class),
                getService(RegistrationDataValidator.class))
        ));
        map.put(StartVerificationCommand.class, buildWrapper(() -> new StartVerificationCommand(
                getService(UserService.class),
                getService(VerificationService.class),
                getService(EmailService.class),
                getService(VerificationDataValidator.class)
        )));
        map.put(ChangeUserRoleCommand.class, buildWrapper(() -> new ChangeUserRoleCommand(getService(UserService.class))));
        map.put(ChangeUserStatusCommand.class, buildWrapper(() -> new ChangeUserStatusCommand(getService(UserService.class))));
        map.put(LoginCommand.class, buildWrapper(() -> new LoginCommand(getService(UserService.class), getService(I18nManager.class))));
        map.put(ShowAllUsersCommand.class, buildWrapper(() -> new ShowAllUsersCommand(getService(UserService.class))));
        map.put(ShowAllVerificationsCommand.class, buildWrapper(() -> new ShowAllVerificationsCommand(getService(VerificationService.class))));
        map.put(RegistrationConfirmationCommand.class, buildWrapper(() -> new RegistrationConfirmationCommand(getService(UserService.class))));
        map.put(ShowTrainerInfoCommand.class, buildWrapper(() -> new ShowTrainerInfoCommand(getService(UserService.class))));
        map.put(ShowUserInfoCommand.class, buildWrapper(() -> new ShowUserInfoCommand(getService(UserService.class))));
        map.put(ShowVerificationInfoCommand.class, buildWrapper(() -> new ShowVerificationInfoCommand(getService(VerificationWebFacade.class))));
        map.put(ChangeFinalStatusCommand.class, buildWrapper(() -> new ChangeFinalStatusCommand(getService(VerificationService.class))));
        map.put(ChangeTrainerScoreCommand.class, buildWrapper(() -> new ChangeTrainerScoreCommand(
                getService(VerificationService.class),
                getService(UserService.class),
                getService(EmailService.class),
                getService(AdmissionScoreCheckService.class)
        )));
        map.put(ChangeTrainerVerificationDateCommand.class, buildWrapper(() -> new ChangeTrainerVerificationDateCommand(getService(VerificationService.class))));
        map.put(ChangeExaminerVerificationDateCommand.class, buildWrapper(() -> new ChangeExaminerVerificationDateCommand(getService(VerificationService.class))));
        map.put(ChangeTrainerCharacteristicCommand.class, buildWrapper(() -> new ChangeTrainerCharacteristicCommand(getService(VerificationService.class))));
        map.put(WelcomeStudentCommand.class, buildWrapper(() -> new WelcomeStudentCommand(
                getService(UserService.class),
                getService(VerificationWebFacade.class))));
        map.put(ChangeUserImageCommand.class, buildWrapper(() -> new ChangeUserImageCommand(getService(UserService.class))));
        map.put(EditUserDataCommand.class, buildWrapper(() -> new EditUserDataCommand(getService(UserService.class), getService(EditUserDataValidator.class))));
        map.put(AddNewUserCommand.class, buildWrapper(() -> new AddNewUserCommand(
                getService(UserService.class),
                getService(RegistrationByAdminValidator.class),
                getService(I18nManager.class))));
    }


    @Override
    public <T> T getService(Class<T> tClass) {
        LazyWrapper lazyWrapper = map.get(tClass);
        if (lazyWrapper == null) {
            throw new NoSuchSingletonException("Application doesn't have singlton for: " + tClass);
        } else {
            return (T) lazyWrapper.getSingleton();
        }
    }

    private static class AppContextImplHolder {
        public static final AppContext HOLDER_INSTANCE = new AppContextImpl();
    }

    public static AppContext getInstance() {
        return AppContextImpl.AppContextImplHolder.HOLDER_INSTANCE;
    }

}
