package org.velichko.finalproject.controller.command;

import org.velichko.finalproject.controller.command.admin.ChangeUserRoleCommand;
import org.velichko.finalproject.controller.command.admin.ChangeUserStatusCommand;
import org.velichko.finalproject.controller.command.admin.ShowAllUsersCommand;
import org.velichko.finalproject.controller.command.admin.ShowAllVerificationsCommand;
import org.velichko.finalproject.controller.command.common.*;
import org.velichko.finalproject.controller.command.examiner.ChangeExaminerVerificationDateCommand;
import org.velichko.finalproject.controller.command.examiner.ChangeFinalStatusCommand;
import org.velichko.finalproject.controller.command.examiner.ShowAllApprovedProjectsCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationCommand;
import org.velichko.finalproject.controller.command.newuser.RegistrationConfirmationCommand;
import org.velichko.finalproject.controller.command.student.StartVerificationCommand;
import org.velichko.finalproject.controller.command.student.WelcomeStudentCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerCharacteristicCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerScoreCommand;
import org.velichko.finalproject.controller.command.trainer.ChangeTrainerVerificationDateCommand;
import org.velichko.finalproject.controller.command.trainer.ShowTrainerInfoCommand;
import org.velichko.finalproject.core.AppContextImpl;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.util.Arrays;
import java.util.List;

import static org.velichko.finalproject.logic.entity.type.UserRole.*;

public enum CommandName {

    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand(), Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    REGISTRATION(AppContextImpl.getInstance().getService(RegistrationCommand.class), List.of(ANONYMOUS)),
    START_VERIFICATION(AppContextImpl.getInstance().getService(StartVerificationCommand.class), List.of(STUDENT)),
    LOGIN(AppContextImpl.getInstance().getService(LoginCommand.class), List.of(ANONYMOUS)),
    CHANGE_USER_ROLE(AppContextImpl.getInstance().getService(ChangeUserRoleCommand.class), List.of(ADMIN)),
    CHANGE_USER_STATUS(AppContextImpl.getInstance().getService(ChangeUserStatusCommand.class), List.of(ADMIN)),
    SHOW_ALL_USERS(AppContextImpl.getInstance().getService(ShowAllUsersCommand.class), List.of(ADMIN)),
    SHOW_ALL_VERIFICATIONS(AppContextImpl.getInstance().getService(ShowAllVerificationsCommand.class),
            Arrays.asList(ADMIN, TRAINER, EXAMINER, CHIEF)),
    SHOW_ALL_APPROVED_PROJECTS(AppContextImpl.getInstance().getService(ShowAllApprovedProjectsCommand.class)),
    REGISTRATION_CONFIRMATION_COMMAND(AppContextImpl.getInstance().getService(RegistrationConfirmationCommand.class)),
    SHOW_TRAINER_INFO(AppContextImpl.getInstance().getService(ShowTrainerInfoCommand.class), Arrays.asList(ADMIN, TRAINER)),
    SHOW_USER_INFO(AppContextImpl.getInstance().getService(ShowUserInfoCommand.class),
            Arrays.asList(ADMIN, TRAINER, STUDENT, EXAMINER, CHIEF)),
    SHOW_VERIFICATION_INFO(AppContextImpl.getInstance().getService(ShowVerificationInfoCommand.class),
            Arrays.asList(TRAINER, STUDENT, EXAMINER, CHIEF)),
    EDIT_USER_DATA(AppContextImpl.getInstance().getService(EditUserDataCommand.class), Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    CHANGE_FINAL_STATUS(AppContextImpl.getInstance().getService(ChangeFinalStatusCommand.class), List.of(EXAMINER)),
    CHANGE_TRAINER_SCORE(AppContextImpl.getInstance().getService(ChangeTrainerScoreCommand.class), List.of(TRAINER)),
    CHANGE_TRAINER_VERIFICATION_DATE(AppContextImpl.getInstance().getService(ChangeTrainerVerificationDateCommand.class), List.of(TRAINER)),
    CHANGE_EXAMINER_VERIFICATION_DATE(AppContextImpl.getInstance().getService(ChangeExaminerVerificationDateCommand.class), List.of(EXAMINER)),
    CHANGE_TRAINER_CHARACTERISTIC(AppContextImpl.getInstance().getService(ChangeTrainerCharacteristicCommand.class), List.of(TRAINER)),
    CHANGE_USER_IMAGE(AppContextImpl.getInstance().getService(ChangeUserImageCommand.class), Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    REDIRECT_STUDENT(AppContextImpl.getInstance().getService(WelcomeStudentCommand.class)),
    REDIRECT_MAIN(new ToMainPageCommand()),
    REDIRECT_VERIFICATION_INFO(AppContextImpl.getInstance().getService(ShowVerificationInfoCommand.class)),
    WRONG_COMMAND(new WrongCommand()),
    TO_MAIN_PAGE(new ToMainPageCommand()),
    TO_LOGIN_PAGE(new ToLoginPageCommand(), List.of(ANONYMOUS)),
    TO_REGISTRATION_PAGE(new ToRegistrationPageCommand(), List.of(ANONYMOUS)),
    TO_EDIT_USER_DATA_PAGE(new ToEditUserDataPageCommand(), Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT));

    private final Command command;
    private final List<UserRole> accessLevel;

    CommandName(Command command) {
        this.command = command;
        this.accessLevel = Arrays.asList(ADMIN, TRAINER, EXAMINER, CHIEF, STUDENT, ANONYMOUS);
    }

    CommandName(Command command, List<UserRole> accessLevel) {
        this.command = command;
        this.accessLevel = accessLevel;
    }

    public Command getCommand() {
        return command;
    }

    public List<UserRole> getAccessLevel() {
        return accessLevel;
    }
}
