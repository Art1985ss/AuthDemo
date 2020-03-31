package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserValidationService {
    private Set<UserValidation> validations;

    @Autowired
    public UserValidationService(Set<UserValidation> validations) {
        this.validations = validations;
    }

    public void validate(User user){
        validations.forEach(v-> v.validate(user));
    }
}
