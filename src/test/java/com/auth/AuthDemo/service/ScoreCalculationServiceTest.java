package com.auth.AuthDemo.service;

import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.service.validation.user.UserValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class ScoreCalculationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TestService testService;

    @Mock
    private DtoTestKC dtoTestKC;

    ScoreCalculationService scoreCalculationService;

    private final BigDecimal expectedScore = new BigDecimal(0.5);
    private BigDecimal actualScore;
    private List<DtoQuestion> questionList = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private DtoQuestion dtoQuestion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = Mockito.mock(UserService.class);
        testService = Mockito.mock(TestService.class);

        answers.add("First");
        answers.add("Second");
        answers.add("Third");
        answers.add("Fourth");

        dtoQuestion = new DtoQuestion();
        dtoQuestion.setId(1L);
        dtoQuestion.setQuestion("Question1");
        dtoQuestion.setAnswers(answers);
        dtoQuestion.setCorrectAnswer("First");
        dtoQuestion.setUserAnswer("Second");
        questionList.add(dtoQuestion);

        dtoQuestion = new DtoQuestion();
        dtoQuestion.setId(2L);
        dtoQuestion.setQuestion("Question2");
        dtoQuestion.setAnswers(answers);
        dtoQuestion.setUserAnswer("Second");
        dtoQuestion.setCorrectAnswer("Second");
        questionList.add(dtoQuestion);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTestScore() {
        when(dtoTestKC.getScore()).thenReturn(BigDecimal.ZERO);
        when(dtoTestKC.getQuestionList()).thenReturn(questionList);

        scoreCalculationService = new ScoreCalculationService(userService, testService);
        actualScore = scoreCalculationService.getTestScore(dtoTestKC);

        assertEquals(expectedScore, actualScore);
    }
}