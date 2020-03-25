package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.Test;
import org.springframework.stereotype.Repository;

@Repository
public class TempTestRepository {
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
