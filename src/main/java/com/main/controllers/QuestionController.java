package com.main.controllers;

import com.main.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.services.QuestionService;

import java.awt.desktop.QuitEvent;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuesbyCategory(@PathVariable String category)
    {
        return questionService.getQuesbyCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable int id)
    {
        return questionService.deleteQuestion(id);
    }
}
