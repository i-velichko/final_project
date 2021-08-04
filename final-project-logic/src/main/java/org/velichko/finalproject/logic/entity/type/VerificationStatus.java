package org.velichko.finalproject.logic.entity.type;

public enum VerificationStatus {
    DRAFT(1),
    WAIT_FOR_TRAINER_CHECK(2),
    WAIT_FOR_EXAMINER_CHECK(3),
    FINISHED(4);


    private final int id;

    VerificationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
