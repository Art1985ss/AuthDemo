package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.stereotype.Component;

/**
 * Interface for test name validations
 */
@Component
public interface TestValidation {
    void validate(TestKC testKC);

    /**
     * Default method for checking if TestKC object is not null
     * @param testKC TestKC object to be checked
     */
    default void checkNull(TestKC testKC) {
        if (testKC == null)
            throw new ValidationException("Entity should exist!");
    }

}
