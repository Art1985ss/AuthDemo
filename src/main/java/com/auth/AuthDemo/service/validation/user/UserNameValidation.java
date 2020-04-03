package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNameValidation implements UserValidation {
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 32;
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
        if(name.length() < MIN_NAME_LENGTH)
            throw  new ValidationException("User name should be at least " + MIN_NAME_LENGTH + " characters long.");
    }

    public void checkMaxLength(String name){
        if (name.length() > MAX_NAME_LENGTH)
            throw new ValidationException("User name should not be longer than " + MAX_NAME_LENGTH + "characters long.");
    }
}
