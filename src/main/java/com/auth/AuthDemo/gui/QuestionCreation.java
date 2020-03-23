package com.auth.AuthDemo.gui;

import com.auth.AuthDemo.entity.Answer;
import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionCreation extends JPanel {
    private JTextField[] textFields;
    private QuestionService questionService;

    @Autowired
    public QuestionCreation(QuestionService questionService) {
        this.questionService = questionService;
        GridLayout layout = new GridLayout(0, 2, 2, 2);
        this.setLayout(layout);


        String[] fieldNames = {"Category", "Question", "Answer a", "Answer b", "Answer c", "Answer d", "Correct answer"};
        textFields = new JTextField[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            JLabel label = new JLabel(fieldNames[i], JLabel.TRAILING);
            textFields[i] = new JTextField();
            label.setLabelFor(textFields[i]);
            this.add(label);
            this.add(textFields[i]);
            System.out.println(i);
        }
        JButton button = new JButton("Add new question");
        addListener(button);
        this.add(button);
        this.setVisible(true);
    }

    private void addListener(JButton button) {
        button.addActionListener(e -> {
            Question question = new Question();
            question.setCategory(textFields[0].getText());
            question.setQuestion(textFields[1].getText());
            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(textFields[2].getText()));
            answers.add(new Answer(textFields[3].getText()));
            answers.add(new Answer(textFields[4].getText()));
            answers.add(new Answer(textFields[5].getText()));
            question.setAnswers(answers);
            question.setCorrectAnswer(textFields[6].getText());
            System.out.println(question);
            questionService.createQuestion(question);
            System.out.println("Button pressed");
        });
    }
}
