package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Data;
import com.auth.AuthDemo.entity.Student;
import com.auth.AuthDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    public List<Data> getData(String name){
        Student student = studentService.findByName(name);
        return null;
    }
}
