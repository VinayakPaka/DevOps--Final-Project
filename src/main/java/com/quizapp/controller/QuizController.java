package com.quizapp.controller;

import com.quizapp.model.Question;
import com.quizapp.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return quizService.getAllQuestions();
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestBody Map<String, String> payload) {
        String questionId = payload.get("id");
        String answer = payload.get("answer");
        boolean isCorrect = quizService.checkAnswer(questionId, answer);
        return isCorrect ? "Correct!" : "Incorrect!";
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    
}
