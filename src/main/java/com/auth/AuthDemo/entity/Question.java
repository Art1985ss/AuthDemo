package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for basic structure of Question object to be stored in database. All necessary fields are marked with
 * corresponding JPA annotations and appropriate getters and setters are generated.
 * Question object is bound to question_answers entity using many-to-many relationship and
 * to TestKC object using one-to-many relationship.
 */
@Entity(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "question")
    private String question;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_answers",
            joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer")
    private List<String> answers = new ArrayList<>();

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToMany(mappedBy = "questionList", fetch = FetchType.LAZY)
    private List<TestKC> testKCList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<TestKC> getTestKCList() {
        return testKCList;
    }

    public void setTestKCList(List<TestKC> testKCList) {
        this.testKCList = testKCList;
    }

    public boolean getResult(String providedAnswer) {
        return correctAnswer.equalsIgnoreCase(providedAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id.equals(question1.id) &&
                category.equals(question1.category) &&
                question.equals(question1.question) &&
                answers.equals(question1.answers) &&
                correctAnswer.equals(question1.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, question, answers, correctAnswer);
    }
}
