package org.velichko.finalproject.webfacade;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.Optional;

public interface VerificationWebFacade {
     Router getVerificationInfoByVerificationIdAndUserRole(long verificationId, HttpServletRequest request, UserRole userRole) throws ServiceException;
     Optional<Long> getVerificationIdByStudentId(long userId) throws ServiceException;
}
