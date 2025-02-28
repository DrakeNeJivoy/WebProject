package com.example.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyRequest {
    private String title;
    private List<QuestionRequest> questions;
}
