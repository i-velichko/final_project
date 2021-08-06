package org.velichko.finalproject.core;

import org.velichko.finalproject.controller.command.admin.ChangeUserRoleCommand;
import org.velichko.finalproject.controller.command.admin.ShowAllUsersCommand;
import org.velichko.finalproject.controller.command.admin.ShowAllVerificationsCommand;
import org.velichko.finalproject.controller.command.common.LoginCommand;
import org.velichko.finalproject.controller.command.common.ShowVerificationInfoCommand;
import org.velichko.finalproject.controller.command.examiner.ChangeExaminerVerificationDateCommand;
import org.velichko.finalproject.controller.command.examiner.ChangeFinalStatusCommand;
import org.velichko.finalproject.controller.command.examiner.ShowAllApprovedProjectsCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationConfirmationCommand;
import org.velichko.finalproject.controller.command.student.StartVerificationCommand;
import org.velichko.finalproject.controller.command.student.WelcomeStudentCommand;
import org.velichko.finalproject.controller.command.trainer.*;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.VerificationDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.dao.impl.VerificationDaoImpl;
import org.velichko.finalproject.logic.service.*;
import org.velichko.finalproject.logic.service.impl.AdmissionScoreCheckServiceImpl;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.logic.service.impl.VerificationServiceImpl;
import org.velichko.finalproject.logic.util.RegistrationConfirmatory;
import org.velichko.finalproject.validator.BaseDataValidator;
import org.velichko.finalproject.validator.RegistrationDataValidator;
import org.velichko.finalproject.validator.VerificationDataValidator;
import org.velichko.finalproject.webfacade.VerificationWebFacade;

import java.util.HashMap;
import java.util.Map;

public class AppContextImpl implements AppContext {

    private Map<Class, Object> map = new HashMap<>();

    private AppContextImpl() {
        map.put(UserDao.class, new UserDaoImpl());
        map.put(EmailService.class, new EmailServiceImpl());
        map.put(AdmissionScoreCheckService.class, new AdmissionScoreCheckServiceImpl());
        map.put(VerificationDao.class, new VerificationDaoImpl());
        map.put(UserService.class, new UserServiceImpl(getService(UserDao.class)));
        map.put(UserService.class, new UserServiceImpl(getService(UserDao.class)));
        map.put(VerificationService.class, new VerificationServiceImpl(getService(VerificationDao.class)));
        map.put(VerificationWebFacade.class, new VerificationWebFacade(getService(VerificationService.class)));
        map.put(I18nManager.class, new I18nManager());
        map.put(BaseDataValidator.class, new RegistrationDataValidator(getService(UserService.class), getService(I18nManager.class)));
        map.put(BaseDataValidator.class, new VerificationDataValidator(getService(UserService.class), getService(I18nManager.class)));
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
                getService(EmailService.class),
                getService(VerificationDataValidator.class)
        ));
        map.put(ChangeUserRoleCommand.class, new ChangeUserRoleCommand(getService(UserService.class)));
        map.put(LoginCommand.class, new LoginCommand(getService(UserService.class)));
        map.put(ShowAllUsersCommand.class, new ShowAllUsersCommand(getService(UserService.class)));
        map.put(ShowAllVerificationsCommand.class, new ShowAllVerificationsCommand(getService(VerificationService.class)));
        map.put(ShowAllApprovedProjectsCommand.class, new ShowAllApprovedProjectsCommand(getService(VerificationService.class)));
        map.put(RegistrationConfirmationCommand.class, new RegistrationConfirmationCommand(getService(UserService.class)));
        map.put(ShowTrainerInfoCommand.class, new ShowTrainerInfoCommand(getService(UserService.class)));
        map.put(ShowStudentInfoCommand.class, new ShowStudentInfoCommand(getService(UserService.class)));
        map.put(ShowVerificationInfoCommand.class, new ShowVerificationInfoCommand(getService(VerificationWebFacade.class)));
        map.put(ChangeFinalStatusCommand.class, new ChangeFinalStatusCommand(getService(VerificationService.class)));
        map.put(ChangeTrainerScoreCommand.class, new ChangeTrainerScoreCommand(
                getService(VerificationService.class),
                getService(UserService.class),
                getService(EmailService.class),
                getService(AdmissionScoreCheckService.class)
                ));
        map.put(ChangeTrainerVerificationDateCommand.class, new ChangeTrainerVerificationDateCommand(getService(VerificationService.class)));
        map.put(ChangeExaminerVerificationDateCommand.class, new ChangeExaminerVerificationDateCommand(getService(VerificationService.class)));
        map.put(ChangeTrainerCharacteristicCommand.class, new ChangeTrainerCharacteristicCommand(getService(VerificationService.class)));
        map.put(WelcomeStudentCommand.class, new WelcomeStudentCommand(
                getService(UserService.class),
                getService(VerificationWebFacade.class)));

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
