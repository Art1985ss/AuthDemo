package com.auth.AuthDemo.service;

import com.auth.AuthDemo.dto.DtoTestKC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * ScoreCalculation service uses UserService and TestService for calculating scores for tests performed by
 * regular users. Scores are calculated performing checks for user answer against correct answer stored in
 * Question entity. If match is found value 1 is retrieved (otherwise 0). Sum of retrieved values is divided
 * by question count after.
 */
@Service
public class ScoreCalculationService {
    private UserService userService;
    private TestService testService;

    @Autowired
    public ScoreCalculationService(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    public BigDecimal getTestScore(DtoTestKC dtoTestKC){
        if(dtoTestKC.isCompleted())
            return dtoTestKC.getScore();
        BigDecimal score = dtoTestKC.getQuestionList().stream().map(dtoQuestion -> {
            if (dtoQuestion.getUserAnswer().equalsIgnoreCase(dtoQuestion.getCorrectAnswer()))
                return BigDecimal.ONE;
            else
                return BigDecimal.ZERO;
        }).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(dtoTestKC.getQuestionList().size()), new MathContext(2));
        dtoTestKC.setScore(score);
        dtoTestKC.setCompleted(true);
        return score;
    }

}
