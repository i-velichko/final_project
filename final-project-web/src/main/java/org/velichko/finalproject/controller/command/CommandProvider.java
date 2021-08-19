package org.velichko.finalproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.util.List;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.ParamName.*;

public class CommandProvider {
    private static Logger logger = LogManager.getLogger();

    private static final String UNKNOWN_COMMAND = "Unknown command: ";

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        return getCommandName(request).orElseThrow().getCommand();
    }

    public List<UserRole> getCommandAccessLevel(HttpServletRequest request) {
        return getCommandName(request).get().getAccessLevel();
    }

    public Optional<CommandName> getCommandName(HttpServletRequest request) {
        String name = request.getParameter(COMMAND_PARAM);
        CommandName commandName = CommandName.WRONG_COMMAND;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.log(Level.DEBUG, UNKNOWN_COMMAND + name);
        }
        return Optional.of(commandName);
    }

}
