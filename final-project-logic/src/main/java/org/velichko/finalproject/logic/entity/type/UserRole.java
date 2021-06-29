package org.velichko.finalproject.logic.entity.type;

public enum UserRole {
    ADMIN(1),
    STUDENT(4),
    TRAINER(2),
    EXAMINER(3);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
