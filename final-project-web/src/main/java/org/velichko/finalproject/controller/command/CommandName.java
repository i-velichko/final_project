package org.velichko.finalproject.controller.command;

import org.velichko.finalproject.controller.command.admin.*;
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

/**
 * @author Ivan Velichko
 *
 * The enum Command name.
 */
public enum CommandName {

    CHANGE_LOCALE(ChangeLocaleCommand.class),
    LOGOUT(LogoutCommand.class, Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    REGISTRATION(RegistrationCommand.class, List.of(ANONYMOUS)),
    START_VERIFICATION(StartVerificationCommand.class, List.of(STUDENT)),
    LOGIN(LoginCommand.class, List.of(ANONYMOUS)),
    CHANGE_USER_ROLE(ChangeUserRoleCommand.class, List.of(ADMIN)),
    CHANGE_USER_STATUS(ChangeUserStatusCommand.class, List.of(ADMIN)),
    SHOW_ALL_USERS(ShowAllUsersCommand.class, List.of(ADMIN)),
    SHOW_ALL_VERIFICATIONS(ShowAllVerificationsCommand.class, Arrays.asList(ADMIN, TRAINER, EXAMINER, CHIEF)),
    SHOW_ALL_APPROVED_PROJECTS(ShowAllApprovedProjectsCommand.class),
    REGISTRATION_CONFIRMATION_COMMAND(RegistrationConfirmationCommand.class),
    SHOW_TRAINER_INFO(ShowTrainerInfoCommand.class, Arrays.asList(ADMIN, TRAINER)),
    SHOW_USER_INFO(ShowUserInfoCommand.class, Arrays.asList(ADMIN, TRAINER, STUDENT, EXAMINER, CHIEF)),
    SHOW_VERIFICATION_INFO(ShowVerificationInfoCommand.class, Arrays.asList(TRAINER, STUDENT, EXAMINER, CHIEF)),
    EDIT_USER_DATA(EditUserDataCommand.class, Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    CHANGE_FINAL_STATUS(ChangeFinalStatusCommand.class, List.of(EXAMINER)),
    CHANGE_TRAINER_SCORE(ChangeTrainerScoreCommand.class, List.of(TRAINER)),
    CHANGE_TRAINER_VERIFICATION_DATE(ChangeTrainerVerificationDateCommand.class, List.of(TRAINER)),
    CHANGE_EXAMINER_VERIFICATION_DATE(ChangeExaminerVerificationDateCommand.class, List.of(EXAMINER)),
    CHANGE_TRAINER_CHARACTERISTIC(ChangeTrainerCharacteristicCommand.class, List.of(TRAINER)),
    CHANGE_USER_IMAGE(ChangeUserImageCommand.class, Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT)),
    ADD_NEW_USER_COMMAND(AddNewUserCommand.class, List.of(ADMIN)),
    TO_ADD_USER_PAGE_COMMAND(ToAddUserPageCommand.class, List.of(ADMIN)),
    REDIRECT_STUDENT(WelcomeStudentCommand.class, List.of(STUDENT)),
    REDIRECT_MAIN(ToMainPageCommand.class),
    REDIRECT_VERIFICATION_INFO(ShowVerificationInfoCommand.class),
    WRONG_COMMAND(WrongCommand.class),
    TO_MAIN_PAGE(ToMainPageCommand.class),
    TO_LOGIN_PAGE(ToLoginPageCommand.class, List.of(ANONYMOUS)),
    TO_REGISTRATION_PAGE(ToRegistrationPageCommand.class , List.of(ANONYMOUS)),
    TO_EDIT_USER_DATA_PAGE(ToEditUserDataPageCommand.class , Arrays.asList(ADMIN, EXAMINER, TRAINER, CHIEF, STUDENT));

    private final Class<? extends Command> commandClass;
    private final List<UserRole> accessLevel;

    CommandName(Class<? extends Command> commandClass) {
        this.commandClass = commandClass;
        this.accessLevel = Arrays.asList(ADMIN, TRAINER, EXAMINER, CHIEF, STUDENT, ANONYMOUS);
    }

    CommandName(Class<? extends Command> commandClass, List<UserRole> accessLevel) {
        this.commandClass = commandClass;
        this.accessLevel = accessLevel;
    }

    public Command getCommand() {
        return AppContextImpl.getInstance().getService(commandClass);
    }

    public List<UserRole> getAccessLevel() {
        return accessLevel;
    }
}
