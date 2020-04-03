package com.auth.AuthDemo.service.validation.test;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class for basic validation of TestKC objects on given input. Test name is checked against following criteria:
 *      - if it is not null
 *      - if it is unique
 *      - if it corresponds to minimum length (4 characters)
 *      - if it does not exceed maximum length (32 characters)
 * In case if validation fails Validation exception is thrown, user is redirected to error.html and appropriate
 * message is shown.
 */
@Component
public class TestNameValidation implements TestValidation {
    private static final int MIN__LENGTH = 4;
    private static final int MAX__LENGTH = 32;
    private TestRepository testRepository;

    @Autowired
    public TestNameValidation(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public void validate(TestKC testKC) {
        checkNull(testKC);
        String name = testKC.getName();
        checkNameNotNull(name);
        isUnique(name);
        checkMaxLength(name);
        checkNameMinLength(name);
    }

    private void checkNameNotNull(String name){
        if(name == null)
            throw new ValidationException("Test should have name!");
    }

    private void isUnique(String name){
        if(testRepository.findByName(name).isPresent())
            throw new ValidationException("Test name should be unique");
    }

    private void checkNameMinLength(String name){
        if(name.length() < MIN__LENGTH)
            throw  new ValidationException("Test name should be at least " + MIN__LENGTH + " characters long.");
    }

    public void checkMaxLength(String name){
        if (name.length() > MAX__LENGTH)
            throw new ValidationException("Test name should not be longer than " + MAX__LENGTH + "characters long.");
    }
}
