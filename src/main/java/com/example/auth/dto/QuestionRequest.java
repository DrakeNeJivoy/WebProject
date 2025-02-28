package com.example.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String text;
    private List<AnswerOptionRequest> answerOptions;
}
