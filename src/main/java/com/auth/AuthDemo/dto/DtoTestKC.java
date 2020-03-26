package com.auth.AuthDemo.dto;

import com.auth.AuthDemo.entity.UserTests;
import java.util.List;

public class DtoTestKC {
    private Long id;
    private String name;
    private List<DtoQuestion> questionList;
    private int duration;
    private List<UserTests> userTests;

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

    public List<UserTests> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<UserTests> userTests) {
        this.userTests = userTests;
    }
}
