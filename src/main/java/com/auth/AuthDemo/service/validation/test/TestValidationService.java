package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;

import java.util.Set;

public class TestValidationService {
    private Set<TestValidation> validations;

    public TestValidationService(Set<TestValidation> validations) {
        this.validations = validations;
    }

    public void validate(TestKC testKC) {
        validations.forEach(v -> validate(testKC));
    }
}
