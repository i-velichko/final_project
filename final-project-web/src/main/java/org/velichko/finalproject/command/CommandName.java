package org.velichko.finalproject.command;

import org.velichko.finalproject.command.admin.ShowAllUsersCommandImpl;
import org.velichko.finalproject.command.newuser.RegistrationCommand;

public enum CommandName {

    SHOW_ALL_USERS(new ShowAllUsersCommandImpl()),
    REGISTRATION(new RegistrationCommand());
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
