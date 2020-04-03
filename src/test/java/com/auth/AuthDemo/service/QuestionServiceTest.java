package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    private final long expectedId = 12L;
    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    private Question question;

    @BeforeEach
    void setUp() {
        initMocks(this);

        question = new Question();
        question.setId(expectedId);
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
        final List<Question> expected = new ArrayList<Question>() {{
            add(question);
        }};
        when(questionRepository.findAll()).thenReturn(expected);

        final List<Question> actual = questionService.findAll();

        verify(questionRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByCategory() {
        final List<Question> expected = new ArrayList<Question>() {{
            add(question);
        }};
        final String category = "Unit Testing";
        when(questionRepository.findByCategory(category)).thenReturn(expected);

        final List<Question> actual = questionService.findByCategory(category);

        verify(questionRepository).findByCategory(category);
        assertEquals(expected, actual);
    }
}