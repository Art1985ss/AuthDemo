package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * Interface for user validations.
 */
@Component
public interface UserValidation {

    void validate(User user);

    /**
     * Default method for checking if User object is not null
     * @param user User object to be checked
     */
    default void checkNull(User user) {
        if (user == null)
            throw new NoSuchElementException("Entity should exist!");
    }

}
