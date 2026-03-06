package com.school.quiz.repository;

import com.school.quiz.entity.Question;
import com.school.quiz.enums.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubject(Subject subject);
}
