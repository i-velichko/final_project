package org.velichko.finalproject.web;

import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

@WebServlet(name = "showStudentsListServlet", value = "/studentsList")
public class ShowStudentsListServlet extends HttpServlet {
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            List<User> users = userDao.findAll();
            transaction.commit();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/pages/show_all_students.jsp").forward(request, response);

        } catch (DaoException | ServletException | IOException e) {
            e.printStackTrace();
            transaction.end();
        }
    }

    public void destroy() {

    }
}
