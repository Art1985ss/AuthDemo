package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "user_tests")
public class UserTest {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestKC testKC;
    @Column(name = "score")
    private BigDecimal score;
    @Column(name = "completed")
    private boolean completed;

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

    public TestKC getTestKC() {
        return testKC;
    }

    public void setTestKC(TestKC testKC) {
        this.testKC = testKC;
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
        return "UserTest{" +
                "testKC=" + testKC +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest userTest = (UserTest) o;
        return id.equals(userTest.id) &&
                user.equals(userTest.user) &&
                testKC.equals(userTest.testKC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, testKC);
    }
}
