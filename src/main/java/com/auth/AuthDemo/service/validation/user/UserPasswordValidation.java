package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.springframework.stereotype.Component;

/**
 * Class for basic validation of User password on given input. User password is checked against following criteria:
 *      - if it is not null
 *      - if it corresponds to minimum length (4 characters)
 *      - if it does not exceed maximum length (32 characters)
 * In case if validation fails Validation exception is thrown, user is redirected to error.html and appropriate
 * message is shown.
 */

@Component
public class UserPasswordValidation implements UserValidation {
    private static final int MIN_PASS_LENGTH = 4;
    private static final int MAX_PASS_LENGTH = 32;

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
        if (password.length() < MIN_PASS_LENGTH)
            throw new ValidationException("User password should be at least " + MIN_PASS_LENGTH + " characters long.");
    }

    private void checkPasswordMaxLength(String password) {
        if (password.length() > MAX_PASS_LENGTH)
            throw new ValidationException("User password should not be longer than " + MAX_PASS_LENGTH + "characters long.");
    }
}
