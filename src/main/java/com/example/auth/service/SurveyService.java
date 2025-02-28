package com.example.auth.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.auth.dto.*;
import com.example.auth.model.*;
import com.example.auth.repository.SurveyRepository;
import com.example.auth.repository.QuestionRepository;
import com.example.auth.repository.AnswerOptionRepository;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final UserRepository userRepository; // Добавляем объявление

    @Transactional
    public Survey createSurvey(SurveyRequest surveyRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Survey survey = new Survey();
        survey.setTitle(surveyRequest.getTitle());
        survey.setUser(user);
        surveyRepository.save(survey);

        for (QuestionRequest questionRequest : surveyRequest.getQuestions()) {
            Question question = new Question();
            question.setText(questionRequest.getText());
            question.setSurvey(survey);
            questionRepository.save(question);

            for (AnswerOptionRequest answerOptionRequest : questionRequest.getAnswerOptions()) {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setText(answerOptionRequest.getText());
                answerOption.setStatus(answerOptionRequest.getStatus());
                answerOption.setQuestion(question);
                answerOptionRepository.save(answerOption);
            }
        }

        return survey;
    }
}
