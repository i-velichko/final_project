package org.velichko.finalproject.controller.command;

/**
 * @author Ivan Velichko
 *
 * The interface Page name.
 */
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

    String REDIRECT_STUDENT = "controller?command=redirect_student";
    String REDIRECT_TO_MAIN_PAGE = "controller?command=redirect_main";
    String REDIRECT_TO_LOGIN_PAGE = "controller?command=to_login_page";
    String REDIRECT_TO_ADD_USER_PAGE = "controller?command=to_add_user_page_command";
    String REDIRECT_TO_EDIT_USER_DATA_PAGE = "controller?command=to_edit_user_data_page";
    String VERIFICATION_TRAINER_CONTROL = "/pages/verification_trainer_control.jsp";
    String VERIFICATION_EXAMINER_CONTROL = "/pages/verification_examiner_control.jsp";
    String MAIN_PAGE = "/pages/main.jsp";
    String EDIT_USER_DATA = "/pages/edit_user_data.jsp";
    String ADD_USER_PAGE = "/pages/add_user.jsp";
}
