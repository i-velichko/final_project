package org.velichko.finalproject.logic.utill;

import java.util.Base64;

public class PasswordHashGenerator {

    public static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
