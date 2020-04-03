package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import com.auth.AuthDemo.service.validation.test.TestValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TestServiceTest {
    private final Long expectedId = 12l;
    @InjectMocks
    private TestService testService;
    @Mock
    private TestRepository testRepository;
    @Mock
    private TestValidationService testValidationService;
    private TestKC expectedTest;

    @BeforeEach
    void setUp() {
        initMocks(this);

        expectedTest = new TestKC();
        expectedTest.setId(expectedId);
    }

    @Test
    void create() {
        when(testRepository.save(expectedTest)).thenReturn(expectedTest);

        final Long actualId = testService.create(expectedTest);

        verify(testRepository).save(expectedTest);
        verify(testValidationService).validate(expectedTest);
        assertEquals(expectedId, actualId);
    }

    @Test
    void update() {
        when(testRepository.save(expectedTest)).thenReturn(expectedTest);

        final Long actualId = testService.update(expectedTest);

        verify(testRepository).save(expectedTest);
        assertEquals(expectedId, actualId);
    }

    @Test
    void findById() {
        when(testRepository.findById(expectedId)).thenReturn(Optional.of(expectedTest));

        final TestKC actual = testService.findById(expectedId);

        verify(testRepository).findById(expectedId);
        assertEquals(expectedTest, actual);
    }

    @Test
    void deleteById() {
        testService.deleteById(expectedId);
        verify(testRepository).deleteById(expectedId);
    }

    @Test
    void findAll() {
        final List<TestKC> expected = new ArrayList<TestKC>() {{
            add(expectedTest);
        }};
        when(testRepository.findAll()).thenReturn(expected);

        final List<TestKC> actual = testService.findAll();

        verify(testRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByName() {
        final String testName = "Java";
        when(testRepository.findByName(testName)).thenReturn(Optional.of(expectedTest));

        final TestKC actual = testService.findByName(testName);

        verify(testRepository).findByName(testName);
        assertEquals(expectedTest, actual);
    }
}