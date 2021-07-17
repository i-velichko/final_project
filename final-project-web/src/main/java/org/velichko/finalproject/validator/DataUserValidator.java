package org.velichko.finalproject.validator;

public enum DataUserValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "email", "email is incorrect"),
    LOGIN("^[\\w@#$%^&+=]{7,25}$", "login", "login is incorrect or user with this login already exists "),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$", "password", "password is incorrect"),
    CHECK_PASSWORD("checkPassword", "passwords are differ");

    private String regExp;
    private String fieldName;
    private String message;

    DataUserValidator(String regExp, String fieldName, String message) {
        this.regExp = regExp;
        this.fieldName = fieldName;
        this.message = message;
    }

    DataUserValidator(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getRegExp() {
        return regExp;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }


}
