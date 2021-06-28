package org.velichko.finalproject.logic.entity;

import org.velichko.finalproject.logic.entity.type.QuestionDifficult;
import org.velichko.finalproject.logic.entity.type.QuestionType;

public class Question extends Entity {
    private long questionId;
    private String questionText;
    private QuestionDifficult difficult;
    private QuestionType type;

}
