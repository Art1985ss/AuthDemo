package com.auth.AuthDemo.entity;

import org.graalvm.compiler.lir.LIRInstruction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "score")
    private BigDecimal score;
    @OneToMany(mappedBy = "user")
    private List<UserTest> userTests = new ArrayList<>();

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<UserTest> userTests) {
        this.userTests = userTests;
    }

    public void addTestKC(TestKC testKC){
        UserTest userTest = new UserTest();
        userTest.setCompleted(false);
        userTest.setScore(BigDecimal.ZERO);
        userTest.setTestKC(testKC);
        userTests.add(userTest);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userTests=" + userTests +
//                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
