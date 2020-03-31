package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.validation.ValidationException;

public class UserPasswordUserValidation implements UserValidation {
    private static final int MIN__LENGTH = 4;
    private static final int MAX__LENGTH = 32;

    @Override
    public void validate(User user) {
        checkNull(user);
        String password = user.getPassword();
        checkPasswordNotNull(password);
        checkPasswordMinLength(password);
        checkPasswordMaxLength(password);
    }

    private void checkPasswordNotNull(String password) {
        if (password == null)
            throw new ValidationException("User password should not be empty!");
    }

    private void checkPasswordMinLength(String password) {
        if (password.length() < MIN__LENGTH)
            throw new ValidationException("User password should be at least " + MIN__LENGTH + " characters long.");
    }

    private void checkPasswordMaxLength(String password) {
        if (password.length() > MAX__LENGTH)
            throw new ValidationException("User password should not be longer than " + MAX__LENGTH + "characters long.");
    }
}
