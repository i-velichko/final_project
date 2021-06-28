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

@WebServlet(name = "StudentDataServlet", value = "/student")
public class StudentDataServlet extends HttpServlet {
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String git = request.getParameter("git");
        String skills = request.getParameter("skills");
        String projectName = request.getParameter("projectName");
        String login = request.getParameter("login");


        //от сюда комманд
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);

        try {

            User user = userDao.findUserByLogin(login);
            user.setGitLink(git);
            userDao.update(user);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/pages/student_info.jsp").forward(request, response);

        } catch (DaoException | ServletException | IOException e) {
            e.printStackTrace();
            transaction.end();
        }


    }

    public void destroy() {

    }
}
