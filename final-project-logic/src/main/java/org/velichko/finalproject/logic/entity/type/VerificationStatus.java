package org.velichko.finalproject.logic.entity.type;

/**
 * @author Ivan Velichko
 *
 * The enum Verification status.
 */
public enum VerificationStatus {
    /**
     * Draft verification status.
     */
    DRAFT(1),
    /**
     * Wait for trainer check verification status.
     */
    WAIT_FOR_TRAINER_CHECK(2),
    /**
     * Wait for examiner check verification status.
     */
    WAIT_FOR_EXAMINER_CHECK(3),
    /**
     * Finished verification status.
     */
    FINISHED(4);


    private final int id;

    VerificationStatus(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
