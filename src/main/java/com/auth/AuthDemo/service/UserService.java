package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements Service_<User> {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long create(User user) {
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
