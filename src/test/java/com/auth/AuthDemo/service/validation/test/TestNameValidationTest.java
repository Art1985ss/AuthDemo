package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TestNameValidationTest {

    @Mock
    private TestRepository testRepository;
    @InjectMocks
    private TestNameValidation validation;

    @BeforeEach
    void setUp() {
        initMocks(this);

    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_name_validation.csv", numLinesToSkip = 1)
    void validate(int id, String name) {
        TestKC test = new TestKC();
        test.setId((long) id);
        test.setName(name);
    }

    @Test
    void checkMaxLength() {
    }
}