package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Helper class for User and TestKC object binding in databases.
 */
@Embeddable
public class UserTestKey implements Serializable {

    @Column(name = "user_id")
    private Long studentId;

    @Column(name = "test_id")
    private Long testId;

    public UserTestKey(Long studentId, Long testId) {
        this.studentId = studentId;
        this.testId = testId;
    }

    public UserTestKey() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTestKey that = (UserTestKey) o;
        return studentId.equals(that.studentId) &&
                testId.equals(that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, testId);
    }
}
