package com.michael.tachemike.service;

import com.michael.tachemike.model.Workout;
import com.michael.tachemike.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    public void deleteById(Long id) {
        workoutRepository.deleteById(id);
    }
}
