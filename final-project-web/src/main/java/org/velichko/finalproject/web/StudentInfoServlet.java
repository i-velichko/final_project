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

@WebServlet(name = "studentInfoServlet", value = "/student")
public class StudentInfoServlet extends HttpServlet {
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String gitLink = request.getParameter("git");
        String skills = request.getParameter("skills");
        String projectName = request.getParameter("projectName");
        String login = request.getParameter("login");

        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            User user = userDao.findUserByLogin(login);
            transaction.commit();
            user.setGitLink(gitLink);
            userDao.updateUserGitLink(login, gitLink);
            transaction.commit();
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
