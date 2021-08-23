package org.velichko.finalproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.util.List;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.ParamName.COMMAND_PARAM;

/**
 * @author Ivan Velichko
 *
 * The type Command provider.
 */
public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String UNKNOWN_COMMAND = "Unknown command: ";

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param request the request
     * @return the command
     */
    public Command getCommand(HttpServletRequest request) {
        return getCommandName(request).orElseThrow().getCommand();
    }

    /**
     * Gets command access level.
     *
     * @param request the request
     * @return the command access level
     */
    public List<UserRole> getCommandAccessLevel(HttpServletRequest request) {
        return getCommandName(request).get().getAccessLevel();
    }

    /**
     * Gets command name.
     *
     * @param request the request
     * @return the command name
     */
    public Optional<CommandName> getCommandName(HttpServletRequest request) {
        String name = request.getParameter(COMMAND_PARAM);
        CommandName commandName = CommandName.WRONG_COMMAND;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.log(Level.DEBUG, UNKNOWN_COMMAND + name);
        }
        return Optional.of(commandName);
    }

}
