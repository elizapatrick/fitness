package com.fitness.service;

import com.fitness.exception.ResourceNotFoundException;
import com.fitness.model.Training;
import com.fitness.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository repo;

    public TrainingService(TrainingRepository repo) {
        this.repo = repo;
    }

    public Training create(Training t) {
        return repo.save(t);
    }

    public List<Training> list() {
        return repo.findAll();
    }

    public Training get(String id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Training not found"));
    }

    public Training update(String id, Training t) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Training not found");
        t.setId(id);
        return repo.save(t);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<Training> filterByType(String type) {
        return repo.findByTrainingsart(type);
    }
}
