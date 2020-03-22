package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Student;
import com.auth.AuthDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student findByName(String name){
        return studentRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("No user found with name : " + name));
    }
}
