package org.velichko.finalproject.logic.main;

import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;

import java.util.Locale;

public class Main {
    public static void main(String[] args) throws DaoException, ServiceException {
        EmailService emailService = new EmailServiceImpl();
        emailService.sendEmail("showman.velichko@gmail.com", "Ivan, you are perfect developer! ");

        System.out.println("Чето нажал");

    }
}
