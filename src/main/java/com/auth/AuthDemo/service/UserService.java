package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.user.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * UserService class provides interactions with UserRepository. When some CRUD operations needs
 * to be performed on User object, this class needs to be implemented first and appropriate methods are called.
 * UserValidation service is added - for basic validation of test field inputs from user.
 */
@Service
public class UserService implements Service_<User> {
    private UserRepository userRepository;
    private UserValidationService userValidationService;

    @Autowired
    public UserService(UserRepository userRepository, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.userValidationService = userValidationService;
    }

    @Override
    public Long create(User user) {
        userValidationService.validate(user);
        return userRepository.save(user).getId();
    }

    @Override
    public Long update(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user with id " + id + " found"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("No user with name " + name + " found"));
    }
}
