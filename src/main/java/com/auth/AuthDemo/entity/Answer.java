package com.auth.AuthDemo.entity;

import javax.persistence.*;

@Entity(name = "question_options")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "answer")
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String text) {
        this.text = text;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
