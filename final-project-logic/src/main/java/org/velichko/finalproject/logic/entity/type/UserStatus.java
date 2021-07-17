package org.velichko.finalproject.logic.entity.type;

public enum UserStatus {
    ACTIVE(1),
    DELETED(2),
    BLOCKED(3);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
