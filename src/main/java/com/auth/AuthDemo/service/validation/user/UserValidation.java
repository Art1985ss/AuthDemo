package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public interface UserValidation {

    void validate(User user);

    default void checkNull(User user) {
        if (user == null)
            throw new NoSuchElementException("Entity should exist!");
    }

}
