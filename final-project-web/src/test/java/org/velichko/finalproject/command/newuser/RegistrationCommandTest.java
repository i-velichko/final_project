package org.velichko.finalproject.command.newuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.utill.RegistrationConfirmatory;
import org.velichko.finalproject.validator.RegistrationDataValidator;

public class RegistrationCommandTest {

    private RegistrationCommand toTest;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
//        UserService userService = Mockito.mock(UserService.class);
        RegistrationConfirmatory confirmatory = Mockito.mock(RegistrationConfirmatory.class);
        RegistrationDataValidator registrationDataValidator = Mockito.mock(RegistrationDataValidator.class);
        I18nManager i18nManager = Mockito.mock(I18nManager.class);

        toTest = new RegistrationCommand(userService, confirmatory, registrationDataValidator, i18nManager);
    }

    @Test
    public void execute() {

        Router execute = toTest.execute(null);
        //TODO
    }
}