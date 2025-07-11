package com.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entities.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>
{

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numques", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numques);

}
