package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "question")
    private String question;
    @ElementCollection
    @CollectionTable(name = "question_answers",
            joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer")
    private List<String> answers = new ArrayList<>();
    @Column(name = "correct_answer")
    private String correctAnswer;
    @OneToMany(mappedBy = "question")
    private List<UserAnswers> userAnswers;
    @ManyToMany(mappedBy = "questionList", fetch = FetchType.LAZY)
    private List<Test> testList = new ArrayList<>();

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

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public boolean getResult(String providedAnswer) {
        return correctAnswer.equalsIgnoreCase(providedAnswer);
    }

    public boolean getScore(User user) {
        List<UserAnswers> correctAnswers = userAnswers.stream().filter(a -> {
            LocalDate timeNow = LocalDate.now();
            LocalDate timeAnswerProvided = a.getAnswerProvided();
            return a.getUser().equals(user)
                    && a.getAnswerProvided().getYear() == timeNow.getYear()
                    && timeAnswerProvided.getDayOfYear() == timeNow.getDayOfYear()
                    && a.getUserAnswer().equalsIgnoreCase(correctAnswer);

        }).collect(Collectors.toList());
        return !correctAnswers.isEmpty();
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
