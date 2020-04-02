package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import com.auth.AuthDemo.service.validation.test.TestValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TestServiceTest {
    @InjectMocks
    private TestService testService;

    @Mock
    private TestRepository testRepository;
    @Mock
    private TestValidationService testValidationService;

    private TestKC testKC;
    private final Long testId = 12l;

    @BeforeEach
    void setUp() {
        initMocks(this);

        testKC = new TestKC();
        testKC.setId(testId);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        when(testRepository.save(testKC)).thenReturn(testKC);

        final Long actualId = testService.create(testKC);

        verify(testRepository).save(testKC);
        verify(testValidationService).validate(testKC);
        assertEquals(testId, actualId);
    }

    @Test
    void update() {
        when(testRepository.save(testKC)).thenReturn(testKC);

        final Long actualId = testService.update(testKC);

        verify(testRepository).save(testKC);
        assertEquals(testId, actualId);
    }

    @Test
    void findById() {
        when(testRepository.findById(testId)).thenReturn(Optional.of(testKC));

        final TestKC actual = testService.findById(testId);

        verify(testRepository).findById(testId);
        assertEquals(testKC, actual);
    }

    @Test
    void deleteById() {
        testService.deleteById(testId);
        verify(testRepository).deleteById(testId);
    }

    @Test
    void findAll() {
        final List<TestKC> expected = new ArrayList<TestKC>() {{add(testKC);}};
        when(testRepository.findAll()).thenReturn(expected);

        final List<TestKC> actual = testService.findAll();

        verify(testRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByName() {

    }
}