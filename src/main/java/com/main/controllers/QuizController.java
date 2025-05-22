package com.main.controllers;

import com.main.entities.Question;
import com.main.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numques,
                                             @RequestParam String title)
    {
        return quizService.createQuiz(category, numques, title);
    }

}
