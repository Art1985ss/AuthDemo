package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

@Entity(name = "test")
@SecondaryTable(name = "user_tests", pkJoinColumns = @PrimaryKeyJoinColumn(name = "test_id"))
public class TestKC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private int durationMinutes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_questions",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questionList = new ArrayList<>();
    @OneToMany(mappedBy = "testKC")
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

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public boolean addQuestion(Question question) {
        return this.questionList.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questionList.remove(question);
    }

    public Set<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(Set<UserTest> userTests) {
        this.userTests = userTests;
    }

    public boolean addUserTest(UserTest userTest){
        return this.userTests.add(userTest);
    }


    private UserTest createUserTest(User user){
        UserTest userTest = new UserTest();
        userTest.setUser(user);
        userTest.setTestKC(this);
        userTest.setCompleted(true);
        userTest.setScore(BigDecimal.ZERO);
        return userTest;
    }

    @Override
    public String toString() {
        return "TestKC{" +
                "name='" + name + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", questionList=" + questionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestKC testKC = (TestKC) o;
        return durationMinutes == testKC.durationMinutes &&
                id.equals(testKC.id) &&
                name.equals(testKC.name) &&
                questionList.equals(testKC.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, questionList);
    }
}
