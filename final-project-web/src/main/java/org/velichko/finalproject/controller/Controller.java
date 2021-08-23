package org.velichko.finalproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.CommandName;
import org.velichko.finalproject.controller.command.CommandProvider;

import java.io.IOException;
import java.util.Optional;

import static org.velichko.finalproject.controller.command.ParamName.COMMAND_PARAM;
import static org.velichko.finalproject.controller.command.ParamName.REFERER_COMMAND;


/**
 * @author Ivan Velichko
 *
 * The type Controller.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<CommandName> commandName = CommandProvider.getInstance().getCommandName(request);
        if (commandName.isPresent()) {
            Router router = commandName.get().getCommand().execute(request);
            request.setAttribute(REFERER_COMMAND, commandName.get().name());
            if (router.hasError()) {
                response.sendError(router.getErrorCode());
            } else if (router.getRouterType() == Router.RouterType.REDIRECT) {
                response.sendRedirect(router.getPagePath());
            } else {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            }
        } else {
            LOGGER.error("No such command for name: {}", request.getParameter(COMMAND_PARAM));
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
