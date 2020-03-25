package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.Question;

import java.math.BigDecimal;
import java.util.List;

public class DtoTest {
    private Long id;
    private String name;
    private List<DtoQuestion> questionList;
    private BigDecimal score;

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DtoTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionList=" + questionList +
                ", score=" + score +
                '}';
    }
}
