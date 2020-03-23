package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "question")
@SecondaryTable(name = "user_question", pkJoinColumns = @PrimaryKeyJoinColumn(name = "question_id"))
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "question")
    private String question;
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    //@JoinTable(name = "question_options", inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Answer> answers = new ArrayList<>();
    @Column(name = "correct_answer")
    private String correctAnswer;
    @Column(table = "user_question", name = "answer")
    private String studentAnswer;
    @ManyToOne
    @JoinColumn(table = "user_question", name = "user_id")
    private User user;


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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    @Override
    public String toString() {
        return String.format("%s\n", question);
    }
}
