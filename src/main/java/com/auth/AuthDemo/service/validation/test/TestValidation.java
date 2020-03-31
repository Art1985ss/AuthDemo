package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public interface TestValidation {
    void validate(TestKC testKC);

    default void checkNull(TestKC testKC) {
        if (testKC == null)
            throw new ValidationException("Entity should exist!");
    }

}
