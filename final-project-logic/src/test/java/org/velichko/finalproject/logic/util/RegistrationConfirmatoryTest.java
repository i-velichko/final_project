package org.velichko.finalproject.logic.util;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.EmailService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RegistrationConfirmatoryTest {
    private RegistrationConfirmatory toTest;
    private static final String EMAIL_TO = "email@gmail.com";
    private static final String LOGIN = "login";

    @Mock
    private EmailService emailService;

    @BeforeEach
    void init() throws ServiceException {
        openMocks(this);
        when(emailService.sendEmail(any(),any())).thenReturn(true);
        toTest = new RegistrationConfirmatory(emailService);
    }

    @Test
    void sendEmailForConfirmRegistrationSuccess() throws ServiceException {
        String loginKey = toTest.sendEmailForConfirmRegistration(EMAIL_TO, LOGIN);
        String expectedLoginKey = PasswordHashGenerator.encodePassword(LOGIN);
        assertEquals(expectedLoginKey, loginKey);
    }

    @Test
    void sendEmailForConfirmRegistrationFailedWhenLoginIsNull(){
        assertThrows(NullPointerException.class, () -> {
            toTest.sendEmailForConfirmRegistration(EMAIL_TO, null);
        });
    }

    @Test
    void sendEmailForConfirmRegistrationFailedWhenEmailIsNull() throws ServiceException {
        when(emailService.sendEmail(any(),any())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> {
            toTest.sendEmailForConfirmRegistration(null, LOGIN);
        });
    }
}