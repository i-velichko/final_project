package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.Verification;

import java.util.List;

public interface VerificationDao {

    Verification findVerificationByVerificationStatusId(long id);

    Verification findVerificationByFinalStatusId(long id);

    Verification findVerificationByStudentId(long id);

    List<Verification> findVerificationsByTrainerScore(long id);
}
