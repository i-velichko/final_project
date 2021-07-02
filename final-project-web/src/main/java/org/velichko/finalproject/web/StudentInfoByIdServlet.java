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

@WebServlet(name = "studentInfoByIdServlet", value = "/studentById")
public class StudentInfoByIdServlet extends HttpServlet {
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) { //todo сделать дугетопосты
        String userId = request.getParameter("userId");

        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            User user = userDao.findEntityById(Long.parseLong(userId));
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
