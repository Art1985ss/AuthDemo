package com.auth.AuthDemo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListCreationDto implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    private List<String> answers = new ArrayList<>();

    // default and parameterized constructor

    public void addToList(String answer) {
        this.answers.add(answer);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
