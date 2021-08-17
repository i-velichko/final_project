package org.velichko.finalproject.logic.service;

import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 *
 * The interface Verification service.
 */
public interface VerificationService {
    /**
     * Find all verifications list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Verification> findAllVerifications() throws ServiceException;

    /**
     * Read by page list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Verification> readByPage(int page) throws ServiceException;

    /**
     * Gets verification count.
     *
     * @return the verification count
     * @throws ServiceException the service exception
     */
    int getVerificationCount() throws ServiceException;

    /**
     * Create new verification boolean.
     *
     * @param verification the verification
     * @param title        the title
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewVerification(Verification verification, String title) throws ServiceException;

    /**
     * Find verification by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Verification> findVerificationById(long id) throws ServiceException;

    /**
     * Change trainer score boolean.
     *
     * @param verificationId the verification id
     * @param newScore       the new score
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeTrainerScore(Long verificationId, Double newScore) throws ServiceException;

    /**
     * Change trainer verification date by id boolean.
     *
     * @param verificationId the verification id
     * @param dateTime       the date time
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeTrainerVerificationDateById(Long verificationId, String dateTime) throws ServiceException;

    /**
     * Change examiner verification date by id boolean.
     *
     * @param verificationId the verification id
     * @param dateTime       the date time
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeExaminerVerificationDateById(Long verificationId, String dateTime) throws ServiceException;

    /**
     * Change trainer characteristic by id boolean.
     *
     * @param id             the id
     * @param characteristic the characteristic
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeTrainerCharacteristicById(long id, String characteristic) throws ServiceException;

    /**
     * Change final verification status by id boolean.
     *
     * @param id          the id
     * @param finalStatus the final status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeFinalVerificationStatusById(long id, FinalStatus finalStatus) throws ServiceException;

    /**
     * Find all approved verifications list.
     *
     * @param status the status
     * @return the list
     */
    List<Verification> findAllApprovedVerifications(VerificationStatus status);

    /**
     * Change verification status by id boolean.
     *
     * @param verificationId     the verification id
     * @param verificationStatus the verification status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeVerificationStatusById(Long verificationId, VerificationStatus verificationStatus) throws ServiceException;

    /**
     * Find verification by student id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Verification> findVerificationByStudentId(long id) throws ServiceException;

}
