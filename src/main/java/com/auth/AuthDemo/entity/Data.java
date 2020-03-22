package com.auth.AuthDemo.entity;

import javax.persistence.*;

@Entity(name = "student_data")
public class Data {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column(name = "data_text")
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Data{" +
                "text='" + text + '\'' +
                '}';
    }
}
