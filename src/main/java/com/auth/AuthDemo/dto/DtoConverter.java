package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * Class for converting Objects to and from DTO objects.
 * DTO objects are used for data passing to html documents, therefore
 * better encapsulation is achieved compared to passing entity Objects.
 */
public class DtoConverter {

    /**
     * Converts Question object to DTO question object.
     * @param question Question object to be converted
     * @return
     */
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

    /**
     * Converts TestKC object to DTO test object. On DTO object
     * creation test completed flag is set to false.
     * @param testKC TestKC object to be converted
     * @return
     */
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

    /**
     * Converts and binds User and TestKC objects to DTO object.
     * This object is used for setting and retrieving user results
     * @param user User object to be converted
     * @param testKC Test object to be converted
     * @return
     */
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

    /**
     * Converts User object to DTO user object.
     * @param user User object to be converted
     * @return
     */
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

    /**
     * Converts DtoQuestion object back to Question object.
     * @param dtoQuestion DtoQuestion object to be converted.
     * @return
     */
    public static Question fromDto(DtoQuestion dtoQuestion) {
        Question question = new Question();
        question.setId(dtoQuestion.getId());
        question.setCategory(dtoQuestion.getCategory());
        question.setQuestion(dtoQuestion.getQuestion());
        question.setAnswers(dtoQuestion.getAnswers());
        question.setCorrectAnswer(dtoQuestion.getCorrectAnswer());
        return question;
    }

    /**
     * Converts DtoTestKC object back to User object. UserTest object
     * is added to user on executing this method - test for the user is set to completed,
     * results are stored and updated.
     * @param user User object for storing test results.
     * @param dtoTestKC DtoTestKC object to retrieve test results from.
     * @return
     */
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
        return testKC;
    }

    /**
     * Converts DtoTestKC object back to TestKC object.
     * @param dtoTestKC DtoTestKC object to be converted.
     * @return
     */
    public static TestKC fromDto(DtoTestKC dtoTestKC) {
        TestKC testKC = new TestKC();
        testKC.setId(dtoTestKC.getId());
        testKC.setName(dtoTestKC.getName());
        testKC.setDurationMinutes(dtoTestKC.getDuration());
        testKC.setEnabled(dtoTestKC.isEnabled());
        testKC.setQuestionList(dtoTestKC.getQuestionList().stream().map(DtoConverter::fromDto).collect(Collectors.toList()));
        return testKC;
    }

    /**
     * Converts DtoUser object back to User object.
     * @param dtoUser DtoUser object to be converted.
     * @return
     */
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

    /**
     * Method for setting answers to DTO question object from given answer Map. Map is retrived
     * from DtoPropertiesForm object. This conversion ins necessary on question creation passing inputs
     * from question input form on HTML document.
     * @param dtoTestKC
     * @param form
     * @return
     */
    public static DtoTestKC setAnswers(DtoTestKC dtoTestKC, DtoPropertiesForm form) {
        dtoTestKC.getQuestionList().forEach(dtoQuestion -> form.getProperties().forEach((key, value) -> {
            if (key.equals(dtoQuestion.getId()))
                dtoQuestion.setUserAnswer(value);
        }));
        return dtoTestKC;
    }
}
