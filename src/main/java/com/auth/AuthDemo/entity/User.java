package com.auth.AuthDemo.entity;

//import org.graalvm.compiler.lir.LIRInstruction;

import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTest> userTests = new HashSet<>();

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

    public Set<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(Set<UserTest> userTests) {
        this.userTests = userTests;
    }

    public boolean addTest(UserTest userTest){
        this.userTests.remove(userTest);
        return this.userTests.add(userTest);
    }

    public UserTest findUserTest(TestKC testKC){
        return this.userTests.stream().filter(userTest -> userTest.getTestKC().equals(testKC)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("No test %s were registered for user %s", testKC.getName(), this.name)));
    }

    public BigDecimal updateScore(){
        score =  userTests.stream().map(UserTest::getScore).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(userTests.size()), new MathContext(2));
        return score;
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
