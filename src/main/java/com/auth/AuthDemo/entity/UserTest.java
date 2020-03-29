package com.auth.AuthDemo.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "user_tests")
@SelectBeforeUpdate(false)
public class UserTest implements Serializable {
    @EmbeddedId
    private UserTestKey id;
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("test_id")
    @JoinColumn(name = "test_id")
    private TestKC testKC;
    @Column(name = "score")
    private BigDecimal score;
    @Column(name = "completed")
    private boolean completed;

    public UserTestKey getId() {
        return id;
    }

    public void setId(UserTestKey id) {
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
                ", hash=" + hashCode() +
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
