package org.velichko.finalproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);

        try {
            User currentUser = userDao.findUserByLogin(login);
            if (currentUser == null) {
                request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            } else {
                request.setAttribute("user", currentUser);
                switch (currentUser.getRole()) {
                    case ADMIN:
                        break;
                    case STUDENT:
                        request.getRequestDispatcher("/pages/student.jsp").forward(request, response);
                        break;
                    case TRAINER:
                        break;
                    case EXAMINER:
                        break;
                }
            }
        } catch (DaoException | ServletException | IOException e) {
            e.printStackTrace();
            transaction.end();
        }


    }

    public void destroy() {

    }
}