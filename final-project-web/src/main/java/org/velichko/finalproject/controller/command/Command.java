package org.velichko.finalproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;

public interface Command {

    Router execute (HttpServletRequest request);
}
