package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Test;
import com.auth.AuthDemo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> findAll(){
        return testRepository.findAll();
    }
}
