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
import org.velichko.finalproject.controller.command.trainer.*;
import org.velichko.finalproject.core.AppContextImpl;

public enum CommandName {

    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    TO_MAIN_PAGE(new ToMainPageCommand()),
    REGISTRATION(AppContextImpl.getInstance().getService(RegistrationCommand.class)),
    START_VERIFICATION(AppContextImpl.getInstance().getService(StartVerificationCommand.class)),
    LOGIN(AppContextImpl.getInstance().getService(LoginCommand.class)),
    CHANGE_USER_ROLE(AppContextImpl.getInstance().getService(ChangeUserRoleCommand.class)),
    CHANGE_USER_STATUS(AppContextImpl.getInstance().getService(ChangeUserStatusCommand.class)),
    SHOW_ALL_USERS(AppContextImpl.getInstance().getService(ShowAllUsersCommand.class)),
    SHOW_ALL_VERIFICATIONS(AppContextImpl.getInstance().getService(ShowAllVerificationsCommand.class)),
    SHOW_ALL_APPROVED_PROJECTS(AppContextImpl.getInstance().getService(ShowAllApprovedProjectsCommand.class)),
    REGISTRATION_CONFIRMATION_COMMAND(AppContextImpl.getInstance().getService(RegistrationConfirmationCommand.class)),
    SHOW_TRAINER_INFO(AppContextImpl.getInstance().getService(ShowTrainerInfoCommand.class)),
    SHOW_STUDENT_INFO(AppContextImpl.getInstance().getService(ShowStudentInfoCommand.class)),
    SHOW_VERIFICATION_INFO(AppContextImpl.getInstance().getService(ShowVerificationInfoCommand.class)),
    CHANGE_FINAL_STATUS(AppContextImpl.getInstance().getService(ChangeFinalStatusCommand.class)),
    CHANGE_TRAINER_SCORE(AppContextImpl.getInstance().getService(ChangeTrainerScoreCommand.class)),
    CHANGE_TRAINER_VERIFICATION_DATE(AppContextImpl.getInstance().getService(ChangeTrainerVerificationDateCommand.class)),
    CHANGE_EXAMINER_VERIFICATION_DATE(AppContextImpl.getInstance().getService(ChangeExaminerVerificationDateCommand.class)),
    CHANGE_TRAINER_CHARACTERISTIC(AppContextImpl.getInstance().getService(ChangeTrainerCharacteristicCommand.class)),
    REDIRECT_STUDENT(AppContextImpl.getInstance().getService(WelcomeStudentCommand.class)),
    REDIRECT_MAIN(new ToMainPageCommand()),
    REDIRECT_VERIFICATION_INFO(AppContextImpl.getInstance().getService(ShowVerificationInfoCommand.class)),
    WRONG_COMMAND(new WrongCommand());

    private final Command command;
    private int accessLevel;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
