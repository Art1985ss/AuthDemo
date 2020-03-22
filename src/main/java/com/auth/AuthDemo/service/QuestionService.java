package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.Student;
import com.auth.AuthDemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentService studentService;

    public void createQuestion(Question question){
        System.out.println("Saving");
        questionRepository.save(question);
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public List<Question> getStudentQuestions(String name){
        Student student = studentService.findByName(name);
        return student.getQuestionList();
    }
}
