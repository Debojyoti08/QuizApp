package com.main.services;

import com.main.dao.QuestionDao;
import com.main.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService 
{
    @Autowired
    QuestionDao questiondao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuesbyCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questiondao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public String deleteQuestion(int id) {
        questiondao.deleteById(id);
        return "question deleted";
    }
}
