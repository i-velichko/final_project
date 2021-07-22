package org.velichko.finalproject.command;

import org.velichko.finalproject.command.admin.ShowAllUsersCommand;
import org.velichko.finalproject.command.common.ChangeLocaleCommand;
import org.velichko.finalproject.command.common.LoginCommand;
import org.velichko.finalproject.command.common.LogoutCommand;
import org.velichko.finalproject.command.newuser.RegistrationCommand;
import org.velichko.finalproject.command.student.StartVerificationCommand;
import org.velichko.finalproject.command.trainer.ShowStudentInfoCommand;
import org.velichko.finalproject.command.trainer.ShowTrainerInfoCommand;

public enum CommandName {

    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    SHOW_TRAINER_INFO(new ShowTrainerInfoCommand()),
    SHOW_STUDENT_INFO(new ShowStudentInfoCommand()),
    START_VERIFICATION (new StartVerificationCommand());


    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    }
