package com.auth.AuthDemo.dto;

import java.math.BigDecimal;
import java.util.List;

public class DtoTestKC {
    private Long id;
    private String name;
    private List<DtoQuestion> questionList;
    private int duration;
    private BigDecimal score;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DtoQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<DtoQuestion> questionList) {
        this.questionList = questionList;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        return "DtoTestKC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionList=" + questionList +
                ", duration=" + duration +
                ", score=" + score +
                ", completed=" + completed +
                '}';
    }
}

