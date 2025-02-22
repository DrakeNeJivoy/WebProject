package com.example.auth.service;

import com.example.auth.model.AnswerOption;
import com.example.auth.model.Question;
import com.example.auth.model.Survey;
import com.example.auth.repository.AnswerOptionRepository;
import com.example.auth.repository.QuestionRepository;
import com.example.auth.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final SurveyRepository surveyRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerOptionRepository answerOptionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
        this.surveyRepository = surveyRepository;
    }

    public void addQuestion(Long surveyId, String questionText, List<String> answerOptions) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            Question question = new Question();
            question.setText(questionText);
            question.setSurvey(survey);
            questionRepository.save(question);

            for (String optionText : answerOptions) {
                AnswerOption option = new AnswerOption();
                option.setText(optionText);
                option.setQuestion(question);
                answerOptionRepository.save(option);
            }
        } else {
            throw new RuntimeException("Анкета не найдена!");
        }
    }
}
