package com.michael.tachemike.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private int duration; // in minutes

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "workout_id") // This creates a foreign key column in the Exercise table
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(String name, LocalDate date, int duration, List<Exercise> exercises) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.exercises = exercises;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
