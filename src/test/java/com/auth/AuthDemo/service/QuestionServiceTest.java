package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    private QuestionService questionService;
    private Question question;
    private final long expectedId = 12L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        question = new Question();
        question.setId(expectedId);
        questionService = new QuestionService(questionRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        when(questionRepository.save(question)).thenReturn(question);
        assertEquals(expectedId, questionService.create(question));
    }

    @Test
    void update() {
        when(questionRepository.save(question)).thenReturn(question);
        assertEquals(expectedId, questionService.create(question));
    }

    @Test
    void findById() {
        final Optional<Question> result = Optional.of(question);

        when(questionRepository.findById(expectedId)).thenReturn(result);
        assertEquals(question, questionService.findById(expectedId));
    }

    @Test
    void deleteById() {
        questionService.deleteById(expectedId);
        verify(questionRepository).deleteById(expectedId);
    }

    @Test
    void findAll() {

    }

    @Test
    void findByCategory() {
    }
}