package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * QuestionService class provides interactions with QuestionRepository. When some CRUD operations needs
 * to be performed on Question object, this class needs to be implemented first and appropriate methods are called.
 */
@Service
public class QuestionService implements Service_<Question> {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Long create(Question question) {
        return questionRepository.save(question).getId();
    }

    @Override
    public Long update(Question question) {
        return questionRepository.save(question).getId();
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No question with id " + id + " found"));
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategory(category);
    }
}
