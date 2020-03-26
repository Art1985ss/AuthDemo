package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.TestKC;
import org.springframework.stereotype.Repository;

@Repository
public class TempTestRepository {
    private TestKC testKC;

    public TestKC getTestKC() {
        return testKC;
    }

    public void setTestKC(TestKC testKC) {
        this.testKC = testKC;
    }
}
