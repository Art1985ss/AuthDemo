package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.TestKC;
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

    public static DtoTestKC toDto(User user, TestKC testKC) {
        DtoTestKC dtoTestKC = new DtoTestKC();
        dtoTestKC.setId(testKC.getId());
        dtoTestKC.setName(testKC.getName());
        dtoTestKC.setDuration(testKC.getDurationMinutes());
        dtoTestKC.setQuestionList(testKC.getQuestionList().stream().map(q->toDto(user, q)).collect(Collectors.toList()));
        dtoTestKC.setUserTests(testKC.getUserTests());
        return dtoTestKC;
    }

    public static DtoUser toDto(User user){
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setName(user.getName());
        dtoUser.setPassword(user.getPassword());
        dtoUser.setScore(user.getScore());
        dtoUser.setUserTests(user.getUserTests());
        return dtoUser;
    }

    public static Question fromDto(User user, DtoQuestion dtoQuestion){
        Question question = new Question();
        question.setId(dtoQuestion.getId());
        question.setQuestion(dtoQuestion.getQuestion());
        question.setAnswers(dtoQuestion.getAnswers());
        question.setUserAnswer(user, dtoQuestion.getUserAnswer());
        question.setCorrectAnswer(dtoQuestion.getCorrectAnswer());
        return question;
    }

    public static TestKC fromDto(User user, DtoTestKC dtoTestKC){
        TestKC testKC = new TestKC();
        testKC.setId(dtoTestKC.getId());
        testKC.setName(dtoTestKC.getName());
        testKC.setDurationMinutes(dtoTestKC.getDuration());
        testKC.setQuestionList(dtoTestKC.getQuestionList().stream().map(q-> fromDto(user, q)).collect(Collectors.toList()));
        testKC.setUserTests(dtoTestKC.getUserTests());
        return testKC;
    }

    public static User fromDto(DtoUser dtoUser){
        User user = new User();
        user.setId(dtoUser.getId());
        user.setName(dtoUser.getName());
        user.setPassword(dtoUser.getPassword());
        user.setScore(dtoUser.getScore());
        user.setUserTests(dtoUser.getUserTests());
        return user;
    }

}
