package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "user_answers")
public class UserAnswers {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @Column(name = "answer")
    private String userAnswer;
    @Column(name = "answer_provided")
    private LocalDate answerProvided = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public LocalDate getAnswerProvided() {
        return answerProvided;
    }

    public void setAnswerProvided(LocalDate answerProvided) {
        this.answerProvided = answerProvided;
    }

    @Override
    public String toString() {
        return "UserAnswers{" +
                "question=" + question.getId() +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
