package com.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.dao.QuestionDao;
import com.main.dao.QuizDao;
import com.main.entities.Question;
import com.main.entities.QuestionWrapper;
import com.main.entities.Quiz;
import com.main.entities.Response;

import jakarta.transaction.Transactional;

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
        quiz.getQuestions().addAll(questions);
      
        quiz.setTitle(title);
        quiz.setQuestions(quiz.getQuestions());
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsforUser = new ArrayList<>();
        for (Question question : questionsfromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                question.getId(),
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
            );
            questionsforUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsforUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        int i = 0;

        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
