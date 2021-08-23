package org.velichko.finalproject.validator.impl;

/**
 * @author Ivan Velichko
 *
 * The enum User data validator.
 */
public enum UserDataValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
    LOGIN("^[\\w@#$%^&+=]{7,30}$"),
    FIRST_NAME("[a-zA-Zа-яА-ЯЁё]{3,30}"),
    LAST_NAME("[a-zA-Zа-яА-ЯЁё]{3,30}"),
    GIT_LINK("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})"),
    PROJECT_TITLE("^.{1,100}$"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,30}$"),
    CHECK_PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,30}$");

    private String regExp;

    UserDataValidator(String regExp) {
        this.regExp = regExp;
    }

    public String getRegExp() {
        return regExp;
    }

}
