package com.school.quiz.service;

import com.school.quiz.entity.Question;
import com.school.quiz.enums.Subject;
import com.school.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsBySubject(Subject subject) {
        List<Question> questions = new ArrayList<>(questionRepository.findBySubject(subject));
        Collections.shuffle(questions); // ordre aléatoire à chaque partie
        return questions;
    }

    public boolean checkAnswer(Long questionId, String answer) {
        return questionRepository.findById(questionId)
                .map(q -> q.getCorrectAnswer().equalsIgnoreCase(answer))
                .orElse(false);
    }
}
