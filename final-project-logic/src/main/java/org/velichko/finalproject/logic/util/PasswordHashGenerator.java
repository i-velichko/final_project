package org.velichko.finalproject.logic.util;

import java.util.Base64;

/**
 * @author Ivan Velichko
 * The type Password hash generator.
 */
public class PasswordHashGenerator {

    /**
     * Encode password string.
     *
     * @param password the password
     * @return the string
     */
    public static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
