package com.auth.AuthDemo.dto;


import com.auth.AuthDemo.entity.UserTest;

import java.math.BigDecimal;
import java.util.Set;

public class DtoUser {

    private Long id;
    private String name;
    private String password;
    private BigDecimal score;
    private Set<UserTest> userTests;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Set<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(Set<UserTest> userTests) {
        this.userTests = userTests;
    }
}
