package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class TestNameValidationTest {

    @Mock
    private TestRepository testRepository;
    @InjectMocks
    private TestNameValidation testNameValidation;

    @BeforeEach
    void setUp() {
        initMocks(this);

    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_name_validation.csv", numLinesToSkip = 1)
    void validate(final long id, final String name, final String expectedException) {
        final TestKC test = new TestKC();
        test.setId(id);
        test.setName(name);

        try {
            testNameValidation.validate(test);
            assertEquals(expectedException, "");
        } catch (ValidationException e) {
            assertEquals(expectedException, e.getMessage());
        }
    }
}