package com.fitness.service;

import com.fitness.exception.ResourceNotFoundException;
import com.fitness.model.TrainingPlan;
import com.fitness.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {
    private final PlanRepository repo;

    public PlanService(PlanRepository repo) {
        this.repo = repo;
    }

    public TrainingPlan create(TrainingPlan p) {
        return repo.save(p);
    }

    public List<TrainingPlan> list() {
        return repo.findAll();
    }

    public TrainingPlan update(String id, TrainingPlan p) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Plan not found");
        p.setId(id);
        return repo.save(p);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
