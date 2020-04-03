package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class for basic validation of User name on given input. User name is checked against following criteria:
 *      - if it is not null
 *      - if it is unique
 *      - if it corresponds to minimum length (4 characters)
 *      - if it does not exceed maximum length (32 characters)
 * In case if validation fails Validation exception is thrown, user is redirected to error.html and appropriate
 * message is shown.
 */

@Service
public class UserNameValidation implements UserValidation {
    private static final int MIN__LENGTH = 4;
    private static final int MAX__LENGTH = 32;
    private UserRepository userRepository;

    @Autowired
    public UserNameValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) {
        checkNull(user);
        String name = user.getName();
        checkNameNotNull(name);
        isUnique(name);
        checkMaxLength(name);
        checkNameMinLength(name);
    }

    private void checkNameNotNull(String name){
        if(name == null)
            throw new ValidationException("User should have name!");
    }

    private void isUnique(String name){
        if(userRepository.findByName(name).isPresent())
            throw new ValidationException("User name should be unique");
    }

    private void checkNameMinLength(String name){
        if(name.length() < MIN__LENGTH)
            throw  new ValidationException("User name should be at least " + MIN__LENGTH + " characters long.");
    }

    public void checkMaxLength(String name){
        if (name.length() > MAX__LENGTH)
            throw new ValidationException("User name should not be longer than " + MAX__LENGTH + "characters long.");
    }
}
