package org.velichko.finalproject.command;

import org.velichko.finalproject.command.admin.ChangeUserRoleCommand;
import org.velichko.finalproject.command.admin.ChangeUserStatusCommand;
import org.velichko.finalproject.command.admin.ShowAllUsersCommand;
import org.velichko.finalproject.command.admin.ShowAllVerifications;
import org.velichko.finalproject.command.common.ChangeLocaleCommand;
import org.velichko.finalproject.command.common.LoginCommand;
import org.velichko.finalproject.command.common.LogoutCommand;
import org.velichko.finalproject.command.common.ShowVerificationInfoCommand;
import org.velichko.finalproject.command.examiner.ChangeExaminerVerificationDateCommand;
import org.velichko.finalproject.command.newuser.RegistrationCommand;
import org.velichko.finalproject.command.newuser.RegistrationConfirmationCommand;
import org.velichko.finalproject.command.student.StartVerificationCommand;
import org.velichko.finalproject.command.student.WelcomeStudentCommand;
import org.velichko.finalproject.command.trainer.ChangeTrainerScoreCommand;
import org.velichko.finalproject.command.trainer.ChangeTrainerVerificationDateCommand;
import org.velichko.finalproject.command.trainer.ShowStudentInfoCommand;
import org.velichko.finalproject.command.trainer.ShowTrainerInfoCommand;

public enum CommandName {

    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    SHOW_ALL_VERIFICATIONS(new ShowAllVerifications()),
    REGISTRATION(new RegistrationCommand()),
    REGISTRATION_CONFIRMATION_COMMAND(new RegistrationConfirmationCommand()),
    LOGIN(new LoginCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    SHOW_TRAINER_INFO(new ShowTrainerInfoCommand()),
    SHOW_STUDENT_INFO(new ShowStudentInfoCommand()),
    SHOW_VERIFICATION_INFO(new ShowVerificationInfoCommand()),
    START_VERIFICATION(new StartVerificationCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    CHANGE_TRAINER_SCORE(new ChangeTrainerScoreCommand()),
    CHANGE_TRAINER_VERIFICATION_DATE(new ChangeTrainerVerificationDateCommand()),
    CHANGE_EXAMINER_VERIFICATION_DATE(new ChangeExaminerVerificationDateCommand()),
    REDIRECT_STUDENT(new WelcomeStudentCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
