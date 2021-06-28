package org.velichko.finalproject.logic.entity.type;

public enum UserRole {
    ADMIN(1),
    STUDENT(2),
    TRAINER(3),
    EXAMINER(4);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
