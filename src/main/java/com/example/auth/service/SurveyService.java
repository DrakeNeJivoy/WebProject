package com.example.auth.service;

import com.example.auth.model.Survey;
import com.example.auth.model.User;
import com.example.auth.repository.SurveyRepository;
import com.example.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    public SurveyService(SurveyRepository surveyRepository, UserRepository userRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
    }

    public void createSurvey(String title, String userEmail) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Survey survey = new Survey();
            survey.setTitle(title);
            survey.setUser(user);
            surveyRepository.save(survey);
        } else {
            throw new RuntimeException("Пользователь не найден!");
        }
    }
}
