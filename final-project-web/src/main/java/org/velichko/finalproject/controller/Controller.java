package org.velichko.finalproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.command.Command;
import org.velichko.finalproject.command.CommandName;
import org.velichko.finalproject.command.ParamConstant;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router;
        Command command = getCommandName(request).getCommand();
        router = command.execute(request);
        switch (router.getRouterType()) {
            case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            case REDIRECT -> response.sendRedirect(router.getPagePath());
        }

    }

    private CommandName getCommandName(HttpServletRequest request) {
        String name = request.getParameter(ParamConstant.COMMAND_PARAM);
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commandName; //todo try catch
    }
}