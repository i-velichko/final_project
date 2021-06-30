package org.velichko.finalproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setEmail(email);
            user.setRole(UserRole.STUDENT);
            user.setStatus(UserStatus.ACTIVE);
            userDao.create(user);
            if (password.equals(confirmPassword)){
                userDao.changePassword(login, password);
            }
             //todo log или ошибка и вообще наверное это в трай кеч
            transaction.commit();
            request.setAttribute("user", user);
            //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }finally {
            transaction.end();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
