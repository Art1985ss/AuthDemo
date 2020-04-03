package com.auth.AuthDemo.service.validation.user;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

class UserValidationServiceTest {
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 32;
    private static final int MIN_PASS_LENGTH = 4;
    private static final int MAX_PASS_LENGTH = 32;
    UserRepository userRepository = mock(UserRepository.class);
    UserNameValidation nameValidation = new UserNameValidation(userRepository);
    UserPasswordValidation passwordValidation = new UserPasswordValidation();
    UserValidationService victim;
    Set<UserValidation> validations = new HashSet<>();
    User user;

    @BeforeEach
    public void setUp(){
        validations.add(nameValidation);
        validations.add(passwordValidation);
        victim = new UserValidationService(validations);
        user = createUser();
        when(userRepository.findByName(anyString())).thenReturn(Optional.empty());
    }

    @Test
    void objectNull() {
        user = null;
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "Entity should exist!");
    }

    @Test
    void nameNull(){
        user.setName(null);
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User should have name!");
    }

    @Test
    void nameTooShort(){
        user.setName("a");
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User name should be at least " + MIN_NAME_LENGTH + " characters long.");
    }

    @Test
    void nameTooLong(){
        user.setName("this name is so long and so hard to write that user who came out with this idea should go to psychiatric ward");
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User name should not be longer than " + MAX_NAME_LENGTH + "characters long.");
    }

    @Test
    void nameNotUnique(){
        when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User name should be unique");
    }

    @Test
    void passwordNull(){
        user.setPassword(null);
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User password should not be empty!");
    }

    @Test
    void passwordTooShort(){
        user.setPassword("a");
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User password should be at least " + MIN_PASS_LENGTH + " characters long.");
    }

    @Test
    void passwordTooLong(){
        user.setPassword("try to remember this password for a long time, good luck with that");
        Exception exception = Assertions.assertThrows(ValidationException.class, ()-> victim.validate(user));
        Assertions.assertEquals(exception.getMessage(), "User password should not be longer than " + MAX_PASS_LENGTH + "characters long.");
    }

    @Test
    void shouldPassValidation(){
        victim.validate(user);
    }


    private User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setScore(BigDecimal.ONE);
        user.setRole("ROLE_USER");
        user.setPassword("test");
        user.setUserTests(new HashSet<>());
        return user;
    }
}