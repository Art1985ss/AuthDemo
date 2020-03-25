package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "test")
@SecondaryTable(name = "user_tests", pkJoinColumns = @PrimaryKeyJoinColumn(name = "test_id"))
public class Test {
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
    @OneToMany(mappedBy = "test")
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

    public void updateUserScores() {
        this.userTests.forEach(ut -> {
            if (ut.isCompleted()) return;
            BigDecimal score = this.getScore(ut.getUser());
            if (score.compareTo(BigDecimal.ZERO) > 0){
                ut.setCompleted(true);
            }
            ut.setScore(score);
        });
    }

    public BigDecimal getScore(User user){
        return questionList.stream().map(q -> {
            if (q.getScore(user)) {
                return BigDecimal.ONE;
            } else {
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(questionList.size()), new MathContext(2));
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", questionList=" + questionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return durationMinutes == test.durationMinutes &&
                id.equals(test.id) &&
                name.equals(test.name) &&
                questionList.equals(test.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, questionList);
    }
}
