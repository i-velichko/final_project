package org.velichko.finalproject.logic.entity.type;

/**
 * @author Ivan Velichko
 *
 * The enum Final status.
 */
public enum FinalStatus {
    /**
     * Excellent final status.
     */
    EXCELLENT(1),
    /**
     * Good final status.
     */
    GOOD(2),
    /**
     * Maybe final status.
     */
    MAYBE(3),
    /**
     * No hire final status.
     */
    NO_HIRE(4);

    private final int id;

    FinalStatus(int id) {
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
