package org.velichko.finalproject.logic.main;

import org.velichko.finalproject.logic.dao.EntityTransaction;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;
import org.velichko.finalproject.logic.service.impl.EmailServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws DaoException, ServiceException {


        List<Double> scores = new ArrayList<>();
        double score = 10;
        for (int i = 0; i <= 20; i++) {
            scores.add(score);
            score += 0.5;
        }
        scores.forEach(System.out::println);

    }
}
