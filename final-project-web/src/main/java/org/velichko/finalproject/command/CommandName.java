package org.velichko.finalproject.command;

import org.velichko.finalproject.command.admin.ShowAllUsersCommand;
import org.velichko.finalproject.command.newuser.LoginCommand;
import org.velichko.finalproject.command.newuser.RegistrationCommand;

public enum CommandName {

    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand());
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
