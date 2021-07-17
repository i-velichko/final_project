package org.velichko.finalproject.logic.entity.type;

public enum UserRole {
    ADMIN(1),
    TRAINER(2),
    EXAMINER(3),
    STUDENT(4);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
