package org.velichko.finalproject.logic.entity.type;

public enum VerificationStatus {
    DRAFT(1),
    WAIT_FOR_TRAINER_CHECK(2),
    TRAINER_CHECK_DONE(3),
    WAIT_FOR_EXAMINER_CHECK(4),
    EXAMINER_CHECK_DONE(5),
    FINISHED(6);


    private final int id;

    VerificationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
