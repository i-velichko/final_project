package org.velichko.finalproject.logic.entity.type;

/**
 * @author Ivan Velichko
 *
 * The enum User status.
 */
public enum UserStatus {
    /**
     * Active user status.
     */
    ACTIVE(1),
    /**
     * Blocked user status.
     */
    BLOCKED(2),
    /**
     * Deleted user status.
     */
    DELETED(3),
    /**
     * Wait confirmation user status.
     */
    WAIT_CONFIRMATION(4);

    private final int id;

    UserStatus(int id) {
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
