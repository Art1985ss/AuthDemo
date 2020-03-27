package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TestService implements Service_<TestKC> {
    private TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Long create(TestKC testKC) {
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
