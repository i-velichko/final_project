package org.velichko.finalproject.controller.command;

public class PageName {

    public static final String INDEX_PAGE = "index.jsp";
    public static final String REFERER = "referer";
    public static final String LOGIN_PAGE = "pages/login.jsp";
    public static final String SHOW_ALL_USERS = "pages/show_all_users.jsp";
    public static final String SHOW_ALL_VERIFICATIONS = "pages/show_all_verifications.jsp";
    public static final String REGISTRATION_PAGE = "pages/registration.jsp";
    public static final String WELCOME_STUDENT = "pages/student.jsp";

    public static final String WELCOME_TRAINER = "pages/trainer.jsp";
    public static final String WELCOME_EXAMINER = "pages/examiner.jsp";
    public static final String WELCOME_ADMIN = "pages/admin.jsp";

    public static final String TRAINER_INFO = "/pages/trainer_info.jsp";
    public static final String USER_INFO = "pages/user_info.jsp";
    public static final String VERIFICATION_INFO = "/pages/verification_info.jsp";

    public static final String ERROR_PAGE = "/pages/error.jsp";

    public static final String REDIRECT_VERIFICATION = "controller?command=redirect_verification";
    public static final String REDIRECT_STUDENT = "controller?command=redirect_student";
    public static final String REDIRECT_MAIN = "controller?command=redirect_main";
    public static final String REDIRECT_TRAINER = "controller?command=redirect_trainer";
    public static final String REDIRECT_ADMIN = "controller?command=redirect_admin";
    public static final String VERIFICATION_TRAINER_CONTROL = "/pages/verification_trainer_control.jsp";
    public static final String VERIFICATION_EXAMINER_CONTROL = "/pages/verification_examiner_control.jsp";
    public static final String MAIN_PAGE = "/pages/main.jsp";
    public static final String REDIRECT_VERIFICATION_INFO = "controller?command=redirect_verification_info";
    public static final String EDIT_USER_DATA = "/pages/edit_user_data.jsp";


    private PageName () {
    }
}
