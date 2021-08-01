package org.velichko.finalproject.logic.entity;

import org.velichko.finalproject.logic.entity.type.FinalStatus;
import org.velichko.finalproject.logic.entity.type.VerificationStatus;

import java.time.LocalDateTime;

public class Verification extends Entity {
    private long id;
    private String title;
    private User student;
    private User trainer;
    private User examiner;
    private VerificationStatus verificationStatus;
    private LocalDateTime applicationDate;
    private LocalDateTime trainerVerificationDate;
    private double trainerScore;
    private String trainerCharacteristic;
    private LocalDateTime examinerVerificationDate;
    private FinalStatus finalStatus;

    public Verification() {
    }

    public Verification(String title, User student, User trainer, VerificationStatus verificationStatus, LocalDateTime applicationDate) {
        this.title = title;
        this.student = student;
        this.trainer = trainer;
        this.verificationStatus = verificationStatus;
        this.applicationDate = applicationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public User getExaminer() {
        return examiner;
    }

    public void setExaminer(User examiner) {
        this.examiner = examiner;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDateTime getTrainerVerificationDate() {
        return trainerVerificationDate;
    }

    public void setTrainerVerificationDate(LocalDateTime trainerVerificationDate) {
        this.trainerVerificationDate = trainerVerificationDate;
    }

    public double getTrainerScore() {
        return trainerScore;
    }

    public void setTrainerScore(double trainerScore) {
        this.trainerScore = trainerScore;
    }

    public String getTrainerCharacteristic() {
        return trainerCharacteristic;
    }

    public void setTrainerCharacteristic(String trainerCharacteristic) {
        this.trainerCharacteristic = trainerCharacteristic;
    }

    public LocalDateTime getExaminerVerificationDate() {
        return examinerVerificationDate;
    }

    public void setExaminerVerificationDate(LocalDateTime examinerVerificationDate) {
        this.examinerVerificationDate = examinerVerificationDate;
    }

    public FinalStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(FinalStatus finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Verification that = (Verification) o;

        if (getId() != that.getId()) {
            return false;
        }
        if (Double.compare(that.getTrainerScore(), getTrainerScore()) != 0) {
            return false;
        }
        if (getStudent() != null ? !getStudent().equals(that.getStudent()) : that.getStudent() != null) {
            return false;
        }
        if (getTrainer() != null ? !getTrainer().equals(that.getTrainer()) : that.getTrainer() != null) {
            return false;
        }
        if (getExaminer() != null ? !getExaminer().equals(that.getExaminer()) : that.getExaminer() != null) {
            return false;
        }
        if (getVerificationStatus() != that.getVerificationStatus()) {
            return false;
        }
        if (getApplicationDate() != null ? !getApplicationDate().equals(that.getApplicationDate()) : that.getApplicationDate() != null) {
            return false;
        }
        if (getTrainerVerificationDate() != null ? !getTrainerVerificationDate().equals(that.getTrainerVerificationDate()) : that.getTrainerVerificationDate() != null) {
            return false;
        }
        if (getTrainerCharacteristic() != null ? !getTrainerCharacteristic().equals(that.getTrainerCharacteristic()) : that.getTrainerCharacteristic() != null) {
            return false;
        }
        if (getExaminerVerificationDate() != null ? !getExaminerVerificationDate().equals(that.getExaminerVerificationDate()) : that.getExaminerVerificationDate() != null) {
            return false;
        }
        return getFinalStatus() == that.getFinalStatus();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getStudent() != null ? getStudent().hashCode() : 0);
        result = 31 * result + (getTrainer() != null ? getTrainer().hashCode() : 0);
        result = 31 * result + (getExaminer() != null ? getExaminer().hashCode() : 0);
        result = 31 * result + (getVerificationStatus() != null ? getVerificationStatus().hashCode() : 0);
        result = 31 * result + (getApplicationDate() != null ? getApplicationDate().hashCode() : 0);
        result = 31 * result + (getTrainerVerificationDate() != null ? getTrainerVerificationDate().hashCode() : 0);
        temp = Double.doubleToLongBits(getTrainerScore());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTrainerCharacteristic() != null ? getTrainerCharacteristic().hashCode() : 0);
        result = 31 * result + (getExaminerVerificationDate() != null ? getExaminerVerificationDate().hashCode() : 0);
        result = 31 * result + (getFinalStatus() != null ? getFinalStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Verification{");
        sb.append("id=").append(id);
        sb.append(", student=").append(student);
        sb.append(", trainer=").append(trainer);
        sb.append(", examiner=").append(examiner);
        sb.append(", verificationStatus=").append(verificationStatus);
        sb.append(", applicationDate=").append(applicationDate);
        sb.append(", trainerVerificationDate=").append(trainerVerificationDate);
        sb.append(", trainerScore=").append(trainerScore);
        sb.append(", trainerCharacteristic='").append(trainerCharacteristic).append('\'');
        sb.append(", examinerVerificationDate=").append(examinerVerificationDate);
        sb.append(", finalStatus=").append(finalStatus);
        sb.append('}');
        return sb.toString();
    }
}
