package com.main.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)  // Add this
    @JoinTable(
            name = "quiz_questions", // Optional but good to be explicit
            joinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id", referencedColumnName = "id")
    )
    private List<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
