package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.Test;
import com.auth.AuthDemo.entity.User;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DtoConverter {

    public static DtoQuestion toDto(User user, Question question) {
        DtoQuestion dtoQuestion = new DtoQuestion();
        dtoQuestion.setId(question.getId());
        dtoQuestion.setCategory(question.getCategory());
        dtoQuestion.setQuestion(question.getQuestion());
        dtoQuestion.setAnswers(question.getAnswers());
        dtoQuestion.setCorrectAnswer(question.getCorrectAnswer());
        try{
            dtoQuestion.setUserAnswer(question.getUserAnswer(user));
        }catch (NoSuchElementException e){
            dtoQuestion.setUserAnswer("");
        }
        return dtoQuestion;
    }

    public static DtoTest toDto(User user, Test test) {
        DtoTest dtoTest = new DtoTest();
        dtoTest.setId(test.getId());
        dtoTest.setName(test.getName());
        dtoTest.setQuestionList(test.getQuestionList().stream().map(q->toDto(user, q)).collect(Collectors.toList()));
        dtoTest.setScore(test.getScore(user));
        return dtoTest;
    }


}
