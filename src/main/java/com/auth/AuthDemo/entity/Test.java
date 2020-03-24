package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "test")
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
    @ManyToMany(mappedBy = "testList", fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();

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
