package org.velichko.finalproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.command.ParamName;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "trainerInfoServlet", value = "/trainer")
public class TrainerInfoServlet {
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(ParamName.LOGIN_PARAM);
        String password = request.getParameter(ParamName.PASSWORD_PARAM);

        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            Optional<User> user = userDao.findUserByLoginAndPassword(login, password);
            transaction.commit();
            request.setAttribute("user", user);
            request.getRequestDispatcher("/pages/trainer_info.jsp").forward(request, response);

        } catch (DaoException | ServletException | IOException e) {
            e.printStackTrace();
            transaction.end();
        }


    }

    public void destroy() {

    }
}
