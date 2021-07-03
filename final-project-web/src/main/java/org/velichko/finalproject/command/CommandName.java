package org.velichko.finalproject.command;

import org.velichko.finalproject.command.admin.ShowAllUsersCommandImpl;

public enum CommandName {
    SHOW_ALL_USERS(new ShowAllUsersCommandImpl());
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
