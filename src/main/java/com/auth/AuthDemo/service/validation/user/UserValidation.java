package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;

import java.util.NoSuchElementException;

public interface UserValidation {

    void validate(User user);

    default void checkNull(User user){
        if (user == null)
            throw new NoSuchElementException("Entity should exist!");
    }

}
