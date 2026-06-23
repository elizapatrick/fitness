package com.fitness.service;

import com.fitness.model.Exercise;
import com.fitness.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public Exercise create(Exercise e) {
        return repo.save(e);
    }

    public List<Exercise> list() {
        return repo.findAll();
    }

    public Exercise update(String id, Exercise e) {
        e.setId(id);
        return repo.save(e);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
