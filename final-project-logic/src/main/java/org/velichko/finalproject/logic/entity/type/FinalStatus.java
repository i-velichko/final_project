package org.velichko.finalproject.logic.entity.type;

public enum FinalStatus {
    EXCELLENT(1),
    GOOD(2),
    MAYBE(3),
    NO_HIRE(4);

    private final int id;

    FinalStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
