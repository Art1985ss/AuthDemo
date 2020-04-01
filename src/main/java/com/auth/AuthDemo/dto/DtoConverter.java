package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class DtoConverter {

    public static DtoQuestion toDto(Question question) {
        DtoQuestion dtoQuestion = new DtoQuestion();
        dtoQuestion.setId(question.getId());
        dtoQuestion.setCategory(question.getCategory());
        dtoQuestion.setQuestion(question.getQuestion());
        dtoQuestion.setAnswers(question.getAnswers());
        dtoQuestion.setUserAnswer("");
        dtoQuestion.setCorrectAnswer(question.getCorrectAnswer());
        return dtoQuestion;
    }
    public static DtoTestKC toDto(TestKC testKC) {
        DtoTestKC dtoTestKC = new DtoTestKC();
        dtoTestKC.setId(testKC.getId());
        dtoTestKC.setName(testKC.getName());
        dtoTestKC.setDuration(testKC.getDurationMinutes());
        dtoTestKC.setEnabled(testKC.isEnabled());
        dtoTestKC.setQuestionList(testKC.getQuestionList().stream().map(DtoConverter::toDto).collect(Collectors.toList()));
        dtoTestKC.setCompleted(false);
        dtoTestKC.setScore(BigDecimal.ZERO);
        return dtoTestKC;
    }


    public static DtoTestKC toDto(User user, TestKC testKC) {
        DtoTestKC dtoTestKC = new DtoTestKC();
        dtoTestKC.setId(testKC.getId());
        dtoTestKC.setName(testKC.getName());
        dtoTestKC.setDuration(testKC.getDurationMinutes());
        dtoTestKC.setEnabled(testKC.isEnabled());
        dtoTestKC.setQuestionList(testKC.getQuestionList().stream().map(DtoConverter::toDto).collect(Collectors.toList()));
        UserTest userTest = user.findUserTest(testKC);
        dtoTestKC.setCompleted(userTest.isCompleted());
        dtoTestKC.setScore(userTest.getScore());
        return dtoTestKC;
    }

    public static DtoUser toDto(User user) {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setName(user.getName());
        dtoUser.setPassword(user.getPassword());
        dtoUser.setScore(user.getScore());
        dtoUser.setRole(user.getRole());
        dtoUser.setUserTests(user.getUserTests());
        return dtoUser;
    }

    public static Question fromDto(DtoQuestion dtoQuestion) {
        Question question = new Question();
        question.setId(dtoQuestion.getId());
        question.setCategory(dtoQuestion.getCategory());
        question.setQuestion(dtoQuestion.getQuestion());
        question.setAnswers(dtoQuestion.getAnswers());
        question.setCorrectAnswer(dtoQuestion.getCorrectAnswer());
        return question;
    }

    public static TestKC fromDto(User user, DtoTestKC dtoTestKC) {
        TestKC testKC = new TestKC();
        testKC.setId(dtoTestKC.getId());
        testKC.setName(dtoTestKC.getName());
        testKC.setDurationMinutes(dtoTestKC.getDuration());
        testKC.setEnabled(dtoTestKC.isEnabled());
        testKC.setQuestionList(dtoTestKC.getQuestionList().stream().map(DtoConverter::fromDto).collect(Collectors.toList()));
        UserTest userTest = new UserTest();
        userTest.setId(new UserTestKey(user.getId(), testKC.getId()));
        userTest.setUser(user);
        userTest.setTestKC(testKC);
        userTest.setCompleted(dtoTestKC.isCompleted());
        userTest.setScore(dtoTestKC.getScore());
        testKC.addUserTest(userTest);
        //user.addUserTest(userTest);
        return testKC;
    }

    public static TestKC fromDto(DtoTestKC dtoTestKC) {
        TestKC testKC = new TestKC();
        testKC.setId(dtoTestKC.getId());
        testKC.setName(dtoTestKC.getName());
        testKC.setDurationMinutes(dtoTestKC.getDuration());
        testKC.setEnabled(dtoTestKC.isEnabled());
        testKC.setQuestionList(dtoTestKC.getQuestionList().stream().map(DtoConverter::fromDto).collect(Collectors.toList()));
        return testKC;
    }

    public static User fromDto(DtoUser dtoUser) {
        User user = new User();
        user.setId(dtoUser.getId());
        user.setName(dtoUser.getName());
        user.setPassword(dtoUser.getPassword());
        user.setScore(dtoUser.getScore());
        user.setRole(dtoUser.getRole());
        user.setUserTests(dtoUser.getUserTests());
        return user;
    }


    public static DtoTestKC setAnswers(DtoTestKC dtoTestKC, DtoPropertiesForm form) {
        dtoTestKC.getQuestionList().forEach(dtoQuestion -> form.getProperties().forEach((key, value) -> {
            if (key.equals(dtoQuestion.getId()))
                dtoQuestion.setUserAnswer(value);
        }));
        return dtoTestKC;
    }
}
