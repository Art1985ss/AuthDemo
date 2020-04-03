package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service class for providing User object validations based on UserNameUserValidation and UsePasswordUser validation class.
 */
@Service
public class UserValidationService {
    private Set<UserValidation> validations;

    @Autowired
    public UserValidationService(Set<UserValidation> validations) {
        this.validations = validations;
    }



    public void validate(User user) {
        validations.forEach(v -> v.validate(user));
    }
}
