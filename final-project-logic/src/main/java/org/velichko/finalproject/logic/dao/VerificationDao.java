package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.Verification;
import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;
import org.velichko.finalproject.logic.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 *
 * The interface Verification dao.
 */
public interface VerificationDao extends BaseDao<Long, Verification> {

    /**
     * Gets verification count.
     *
     * @return the verification count
     * @throws DaoException the dao exception
     */
    int getVerificationCount() throws DaoException;

    /**
     * Create new verification boolean.
     *
     * @param user        the user
     * @param projectName the project name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createNewVerification(Verification user, String projectName) throws DaoException;

    /**
     * Find verification by student id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Verification> findVerificationByStudentId(Long id) throws DaoException;

    /**
     * Change trainer score by id boolean.
     *
     * @param id    the id
     * @param score the score
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeTrainerScoreById(long id, double score) throws DaoException;

    /**
     * Change trainer characteristic by id boolean.
     *
     * @param id             the id
     * @param characteristic the characteristic
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeTrainerCharacteristicById(long id, String characteristic) throws DaoException;

    /**
     * Change verification status by id boolean.
     *
     * @param id          the id
     * @param finalStatus the final status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeVerificationStatusById(long id, VerificationStatus finalStatus) throws DaoException;

    /**
     * Change final verification status by id boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeFinalVerificationStatusById(long id, FinalStatus status) throws DaoException;

    /**
     * Change trainer verification date by id boolean.
     *
     * @param id       the id
     * @param dateTime the date time
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeTrainerVerificationDateById(long id, String dateTime) throws DaoException;

    /**
     * Change examiner verification date by id boolean.
     *
     * @param id       the id
     * @param dateTime the date time
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeExaminerVerificationDateById(long id, String dateTime) throws DaoException;


}
