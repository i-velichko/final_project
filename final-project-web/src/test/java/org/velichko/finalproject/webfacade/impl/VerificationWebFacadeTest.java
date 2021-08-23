package org.velichko.finalproject.webfacade.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.VerificationService;

import java.time.LocalDateTime;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.logic.entity.type.UserRole.*;
import static org.velichko.finalproject.logic.entity.type.VerificationStatus.DRAFT;


class VerificationWebFacadeTest {
    private VerificationWebFacadeImpl toTest;
    private Verification verification;

    @Mock
    private VerificationService verificationService;
    @Mock
    private HttpServletRequest request;


    @BeforeEach
    void init() throws ServiceException {
        openMocks(this);
        verification = new Verification("title", new User(), new User(), DRAFT, LocalDateTime.now());
        when(verificationService.findVerificationById(anyLong())).thenReturn(Optional.of(verification));
        toTest = new VerificationWebFacadeImpl(verificationService);
    }


    @Test
    void expectedTrainerControlPageWhenTrainerCall() throws ServiceException {
        Router router = toTest.getVerificationInfoByVerificationIdAndUserRole(verification.getId(), request, TRAINER);
        assertEquals(VERIFICATION_TRAINER_CONTROL, router.getPagePath());
    }


    @Test
    void expectedExaminerControlPageWhenExaminerCall() throws ServiceException {
        Router router = toTest.getVerificationInfoByVerificationIdAndUserRole(verification.getId(), request, EXAMINER);
        assertEquals(VERIFICATION_EXAMINER_CONTROL, router.getPagePath());
    }


    @Test
    void expectedCommonVerificationPageWhenAnybodyCall() throws ServiceException {
        Router router = toTest.getVerificationInfoByVerificationIdAndUserRole(verification.getId(), request, STUDENT);
        assertEquals(VERIFICATION_INFO, router.getPagePath());
    }


    @Test
    void expectedIndexPageWhenVerificationIsEmpty() throws ServiceException {
        when(verificationService.findVerificationById(anyLong())).thenReturn(Optional.empty());
        Router router = toTest.getVerificationInfoByVerificationIdAndUserRole(verification.getId(), request, STUDENT);
        assertEquals(INDEX_PAGE, router.getPagePath());
    }

    @Test
    void expectedErrorCode500WhenVerificationIsEmpty() throws ServiceException {
        when(verificationService.findVerificationById(anyLong())).thenReturn(Optional.empty());
        Router router = toTest.getVerificationInfoByVerificationIdAndUserRole(verification.getId(), request, STUDENT);
        assertEquals(SC_INTERNAL_SERVER_ERROR, router.getErrorCode());
    }

}