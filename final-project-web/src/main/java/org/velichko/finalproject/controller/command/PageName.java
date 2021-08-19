package org.velichko.finalproject.controller.command;

public interface PageName {
    String INDEX_PAGE = "index.jsp";
    String REFERER = "referer";
    String LOGIN_PAGE = "pages/login.jsp";
    String SHOW_ALL_USERS = "pages/show_all_users.jsp";
    String SHOW_ALL_VERIFICATIONS = "pages/show_all_verifications.jsp";
    String REGISTRATION_PAGE = "pages/registration.jsp";
    String WELCOME_STUDENT = "pages/student.jsp";

    String WELCOME_TRAINER = "pages/trainer.jsp";
    String WELCOME_EXAMINER = "pages/examiner.jsp";
    String WELCOME_ADMIN = "pages/admin.jsp";

    String TRAINER_INFO = "/pages/trainer_info.jsp";
    String USER_INFO = "pages/user_info.jsp";
    String VERIFICATION_INFO = "/pages/verification_info.jsp";

    String ERROR_PAGE = "/pages/error.jsp";

    String REDIRECT_VERIFICATION = "controller?command=redirect_verification";
    String REDIRECT_STUDENT = "controller?command=redirect_student";
    String REDIRECT_MAIN = "controller?command=redirect_main";
    String REDIRECT_TRAINER = "controller?command=redirect_trainer";
    String REDIRECT_ADMIN = "controller?command=redirect_admin";
    String REDIRECT_TO_LOGIN = "controller?command=to_login_page";
    String VERIFICATION_TRAINER_CONTROL = "/pages/verification_trainer_control.jsp";
    String VERIFICATION_EXAMINER_CONTROL = "/pages/verification_examiner_control.jsp";
    String MAIN_PAGE = "/pages/main.jsp";
    String REDIRECT_VERIFICATION_INFO = "controller?command=redirect_verification_info";
    String EDIT_USER_DATA = "/pages/edit_user_data.jsp";
}
