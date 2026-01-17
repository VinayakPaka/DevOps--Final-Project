package com.quizapp.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {

    @Test
    void testGetAllQuestions() {
        QuizService service = new QuizService();
        assertEquals(3, service.getAllQuestions().size());
    }

    @Test
    void testCheckAnswerCorrect() {
        QuizService service = new QuizService();
        assertTrue(service.checkAnswer("1", "Paris"));
    }

    @Test
    void testCheckAnswerIncorrect() {
        QuizService service = new QuizService();
        assertFalse(service.checkAnswer("1", "Berlin"));
    }
}
