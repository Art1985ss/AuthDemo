package com.auth.AuthDemo.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for converting Question objects to and from DTO. DTO objects ar used for transactions to and from html
 * pages, providing better encapsulation.
 */
public class DtoQuestion {
    private Long id;
    private String category;
    private String question;
    private List<String> answers = new ArrayList<>();
    private String correctAnswer;
    private String userAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "DtoQuestion{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
