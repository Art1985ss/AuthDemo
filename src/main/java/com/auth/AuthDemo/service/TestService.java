package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import com.auth.AuthDemo.service.validation.test.TestValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * TestService class provides interactions with TestRepository. When some CRUD operations needs
 * to be performed on TestKC object, this class needs to be implemented first and appropriate methods are called.
 * TestValidation service is added - for basic validation of test field inputs from user.
 */
@Service
public class TestService implements Service_<TestKC> {
    private TestRepository testRepository;
    private TestValidationService testValidationService;

    @Autowired
    public TestService(TestRepository testRepository, TestValidationService testValidationService) {
        this.testRepository = testRepository;
        this.testValidationService = testValidationService;
    }

    @Override
    public Long create(TestKC testKC) {
        testValidationService.validate(testKC);
        return testRepository.save(testKC).getId();
    }

    @Override
    public Long update(TestKC testKC) {
        return testRepository.save(testKC).getId();
    }

    @Override
    public TestKC findById(Long id) {
        return testRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No test with id " + id + " found"));
    }

    @Override
    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<TestKC> findAll() {
        return testRepository.findAll();
    }

    public TestKC findByName(String name){
        return testRepository.findByName(name).orElseThrow(()-> new NoSuchElementException("No test with name " + name + "found"));
    }
}
