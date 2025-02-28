package com.example.auth.dto;

import lombok.Data;

@Data
public class AnswerOptionRequest {
    private String text;
    private int status = 0; // 0 - правильного ответа нет, 1 - неправильный, 2 - правильный
}
