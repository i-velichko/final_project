package org.velichko.finalproject.validator;

public enum DataUserValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "email", "email is incorrect"),
    LOGIN("^[\\w@#$%^&+=]{7,25}$", "login", "password is incorrect"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$", "password", "login is incorrect");

    private String regExp;
    private String fieldName;
    private String message;

    DataUserValidator(String regExp, String fieldName, String message) {
        this.regExp = regExp;
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
