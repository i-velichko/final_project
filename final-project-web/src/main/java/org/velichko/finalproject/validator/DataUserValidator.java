package org.velichko.finalproject.validator;

public class DataUserValidator {
    private static final String PASSWORD_VALIDATION_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,45}$"; //todo длиннее
    private static final String LOGIN_VALIDATION_PATTERN = "^[\\w@#$%^&+=]{3,25}$";
    private static final String EMAIL_VALIDATION_PATTERN = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; //todo длиннее

    public static boolean isPasswordValid(String password) {
        return password.matches(PASSWORD_VALIDATION_PATTERN);
    }

    public static boolean isLoginValid(String login) {
        return login.matches(LOGIN_VALIDATION_PATTERN);
    }

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_VALIDATION_PATTERN);
    }

}
