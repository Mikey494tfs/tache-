package com.michael.tachemike.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Workout name cannot be empty")
    private String name;

    @NotNull(message = "Workout date cannot be null")
    private LocalDate date;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration; // in minutes

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "workout_id") // This creates a foreign key column in the Exercise table
    @Valid // Ensures that exercises within the list are also validated
    private List<Exercise> exercises = new ArrayList<>();

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
