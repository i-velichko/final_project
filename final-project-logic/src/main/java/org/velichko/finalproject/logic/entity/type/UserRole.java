package org.velichko.finalproject.logic.entity.type;

public enum UserRole {
    ADMIN(1),
    TRAINER(2),
    EXAMINER(3),
    CHIEF(4),
    STUDENT(5);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
