package org.velichko.finalproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.service.impl.UserServiceImpl;
import org.velichko.finalproject.utill.JspHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@WebServlet(name = "content", value = "/content")
public class ContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserServiceImpl();
        try {
            List<User> users = service.readAll();
            req.setAttribute("users", users);
            req.getSession().setAttribute("usersMap", users.stream().collect(toMap(User::getId, User::getLogin)));
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
