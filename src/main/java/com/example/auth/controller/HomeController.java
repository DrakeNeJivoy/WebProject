package com.example.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.auth.service.SurveyService;
import org.springframework.ui.Model;
import com.example.auth.model.Survey;
import java.util.List;
import lombok.RequiredArgsConstructor; // Для использования Lombok

@Controller
@RequiredArgsConstructor // Это автоматически добавит конструктор с инъекцией зависимостей
public class HomeController {

    private final SurveyService surveyService; // Теперь зависимость инжектируется через конструктор

    @GetMapping("/")
    public String home(Model model) {
        List<Survey> surveys = surveyService.getAllSurveys();
        System.out.println("Количество анкет: " + surveys.size());  // Логируем количество анкет
        model.addAttribute("surveys", surveys);
        return "home"; // Это home.html в папке templates
    }

    // Метод для отображения главной страницы с анкетами
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Survey> surveys = surveyService.getAllSurveys();
        System.out.println("Количество анкет: " + surveys.size());  // Логируем количество анкет
        model.addAttribute("surveys", surveys);
        return "home";
    }

    @GetMapping("/home-create-survey")
    public String showCreateSurveyPage() {
        return "create-survey"; // Отображаем страницу создания анкеты
    }
}
