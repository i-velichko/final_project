package org.velichko.finalproject.logic.main;

import org.velichko.finalproject.logic.entity.User;

public class Main {
    public static void main(String[] args) {
        SomeService someService = new SomeService();
//        someService.findAllUsers().forEach(System.out::println);
//        User user = someService.findUSerByID(2);
        User user = someService.findUSerByLogin("Java");
//        User user = someService.findUSerByEmail("mog@gmail.com");
        System.out.println(user);

    }
}
