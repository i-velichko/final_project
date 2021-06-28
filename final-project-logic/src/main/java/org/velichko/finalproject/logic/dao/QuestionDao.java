package org.velichko.finalproject.logic.dao;

import org.velichko.finalproject.logic.entity.Question;

public interface QuestionDao {

    Question findQuestionByDifficultID (long id);
    Question findQuestionByTypeID (long id);

}
