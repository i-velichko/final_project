package org.velichko.finalproject.controller.command;


/**
 * @author Ivan Velichko
 *
 * The interface Message name key.
 */
public interface MessageNameKey {

    String LOGIN_NOT_UNIQUE_KEY = "error.login.not.unique";
    String EMAIL_NOT_UNIQUE_KEY = "error.email.not.unique";
    String EMAIL_NOT_CORRECT_KEY = "error.email.not.correct";
    String LOGIN_NOT_CORRECT_KEY = "error.login.not.correct";
    String PASSWORD_NOT_CORRECT_KEY = "error.password.not.correct";
    String REGISTRATION_SUCCESSFUL_KEY = "registration.complete";
    String REGISTRATION_FAILED_KEY = "registration.failed";
    String GIT_LINK_NOT_UNIQUE_KEY = "error.gitLink.not.unique";
    String GIT_LINK_NOT_CORRECT_KEY = "error.gitLink.not.correct";
    String PROJECT_TITLE_NOT_CORRECT_KEY = "error.projectTitle.not.correct";
    String IMAGE_NOT_UPLOAD_KEY = "error.upload.image.failed";
    String NAME_NOT_CORRECT_KEY = "error.name.not.correct";
    String ROLE_NOT_CORRECT_KEY = "error.role.not.correct";
    String ADD_USER_SUCCESSFUL_KEY = "add.user.is.done";
}
