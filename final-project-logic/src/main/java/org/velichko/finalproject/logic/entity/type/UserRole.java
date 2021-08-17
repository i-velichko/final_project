package org.velichko.finalproject.logic.entity.type;

/**
 * @author Ivan Velichko
 *
 * The enum User role.
 */
public enum UserRole {
    /**
     * Admin user role.
     */
    ADMIN(1),
    /**
     * Trainer user role.
     */
    TRAINER(2),
    /**
     * Examiner user role.
     */
    EXAMINER(3),
    /**
     * Chief user role.
     */
    CHIEF(4),
    /**
     * Student user role.
     */
    STUDENT(5),
    /**
     * Anonymous user role.
     */
    ANONYMOUS(6);

    private final int id;

    UserRole(int id) {
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
