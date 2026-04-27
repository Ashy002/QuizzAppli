package com.school.quiz.entity;

import com.school.quiz.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long   id;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private Subject subject;
}
