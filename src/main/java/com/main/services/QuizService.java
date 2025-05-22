package com.main.services;

import com.main.dao.QuestionDao;
import com.main.dao.QuizDao;
import com.main.entities.Question;
import com.main.entities.Quiz;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionDao quesDao;

    @Autowired
    private QuizDao quizDao;

    @Transactional
    public ResponseEntity<String> createQuiz(String category, int numques, String title) {

        // Get all questions of the selected category
//        List<Question> allQuestions = quesDao.findByCategory(category);
//
//        if (allQuestions.size() < numques) {
//            return new ResponseEntity<>("Not enough questions available", HttpStatus.BAD_REQUEST);
//        }
//
//        // Shuffle and limit
//        Collections.shuffle(allQuestions);
//        List<Question> selectedQuestions = allQuestions.stream()
//                .limit(numques)
//                .collect(Collectors.toList());

//        // Create and save quiz
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(selectedQuestions);
//
//        quizDao.save(quiz); // Will persist quiz and the join table
//
//        return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);

        List<Question> questions = quesDao.findRandomQuestionsByCategory(category, numques);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
