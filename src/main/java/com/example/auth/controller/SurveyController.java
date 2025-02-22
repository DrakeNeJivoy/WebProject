package com.example.auth.controller;

import com.example.auth.model.Survey;
import com.example.auth.service.SurveyService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/create-survey")
    public String showSurveyForm() {
        return "create-survey";
    }

    @PostMapping("/create-survey")
    public String createSurvey(@RequestParam("title") String title, Authentication authentication) {
        String userEmail = authentication.getName(); // Получаем email авторизованного пользователя
        surveyService.createSurvey(title, userEmail);
        return "redirect:/"; // После создания перенаправляем на главную
    }
}
