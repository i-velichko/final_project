package org.velichko.finalproject.logic.service;

/**
 * @author Ivan Velichko
 *
 * The interface Admission score check service.
 */
public interface AdmissionScoreCheckService {
    /**
     * Check score boolean.
     *
     * @param score the score
     * @return the boolean
     */
    boolean checkScore (double score);
}
