package com.quizapp.model;

public class Question {
    private String id;
    private String text;
    private String[] options;
    private String correctAnswer;

    public Question(String id, String text, String[] options, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
