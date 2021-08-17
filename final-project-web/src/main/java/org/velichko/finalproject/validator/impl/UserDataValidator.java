package org.velichko.finalproject.validator.impl;

public enum UserDataValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
    LOGIN("^[\\w@#$%^&+=]{7,25}$"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$"),
    CHECK_PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$");

    private String regExp;

    UserDataValidator(String regExp) {
        this.regExp = regExp;
    }

    public String getRegExp() {
        return regExp;
    }

}
