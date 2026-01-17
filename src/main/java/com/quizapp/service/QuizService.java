package com.quizapp.service;

import com.quizapp.model.Question;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final List<Question> questions = new ArrayList<>();

    public QuizService() {
        questions.add(new Question("1", "What is the capital of France?",
                new String[] { "Berlin", "Madrid", "Paris", "Rome" }, "Paris"));
        questions.add(new Question("2", "What is 2 + 2?", new String[] { "3", "4", "5", "6" }, "4"));
        questions.add(new Question("3", "Who wrote 'Hamlet'?",
                new String[] { "Goethe", "Austen", "Shakespeare", "Dickens" }, "Shakespeare"));
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public boolean checkAnswer(String questionId, String answer) {
        return questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .map(q -> q.getCorrectAnswer().equalsIgnoreCase(answer))
                .orElse(false);
    }
}
