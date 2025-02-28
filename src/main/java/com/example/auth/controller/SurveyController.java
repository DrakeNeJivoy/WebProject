package com.example.auth.controller;

import com.example.auth.dto.SurveyRequest;
import com.example.auth.model.Survey;
import com.example.auth.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/create")
    public ResponseEntity<String> createSurvey(
            @RequestBody SurveyRequest surveyRequest,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        Survey survey = surveyService.createSurvey(surveyRequest, userDetails.getUsername());
        return ResponseEntity.ok("Survey created with ID: " + survey.getId());
    }
}
