package com.michael.tachemike.controller;

import com.michael.tachemike.model.Workout;
import com.michael.tachemike.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Optional<Workout> workout = workoutService.findById(id);
        return workout.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Workout not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@Valid @RequestBody Workout workout) {
        Workout savedWorkout = workoutService.save(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        if (!workoutService.findById(id).isPresent()) {
            throw new NoSuchElementException("Workout not found with ID: " + id);
        }
        workout.setId(id); // Ensure the ID is set for update
        Workout updatedWorkout = workoutService.save(workout);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        if (!workoutService.findById(id).isPresent()) {
            throw new NoSuchElementException("Workout not found with ID: " + id);
        }
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
