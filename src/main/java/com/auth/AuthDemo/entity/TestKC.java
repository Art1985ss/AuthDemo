package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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

    public List<UserTests> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<UserTests> userTests) {
        this.userTests = userTests;
    }

    public boolean isCompleted(User user){
        return userTests.stream().filter(ut-> ut.getUser().equals(user)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("No test %s found for user %s", this.getName(), user.getName())))
                .isCompleted();
    }

    public void setCompleted(User user, boolean completed){
        userTests.stream().filter(ut-> ut.getUser().equals(user)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("No test %s found for user %s", this.getName(), user.getName())))
                .setCompleted(completed);
    }

    public void updateUserScores() {
        this.userTests.forEach(ut -> {
            if (ut.isCompleted()) return;
            BigDecimal score = this.updateUserScore(ut.getUser());
            if (score.compareTo(BigDecimal.ZERO) > 0) {
                ut.setCompleted(true);
            }
            ut.setScore(score);
        });
    }

    public BigDecimal updateUserScore(User user) {
        BigDecimal score = questionList.stream().map(q -> {
            if (q.getScore(user)) {
                return BigDecimal.ONE;
            } else {
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(questionList.size()), new MathContext(2));
        this.setScore(user, score);
        return score;
    }

    public BigDecimal getScore(User user) {
        return userTests.stream().filter(ut -> ut.getUser().equals(user)).findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("No score for test %s from user %s", this.name, user.getName())))
                .getScore();
    }

    public void setScore(User user, BigDecimal score){
        userTests.stream().filter(ut-> ut.getUser().equals(user)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("User %s doesn't have test %s", user.getName(), this.name)))
                .setScore(score);
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
