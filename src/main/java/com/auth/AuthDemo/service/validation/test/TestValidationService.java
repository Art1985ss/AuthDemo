package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service class for providing TestKC object validations based on TestNameValidation class.
 */
@Service
public class TestValidationService {
    private Set<TestValidation> validations;

    public TestValidationService(Set<TestValidation> validations) {
        this.validations = validations;
    }

    public void validate(TestKC testKC) {
        validations.forEach(v -> validate(testKC));
    }
}
