package com.fitness.service;

import com.fitness.exception.ResourceNotFoundException;
import com.fitness.model.Progress;
import com.fitness.repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    private final ProgressRepository repo;

    public ProgressService(ProgressRepository repo) {
        this.repo = repo;
    }

    public Progress create(Progress p) {
        return repo.save(p);
    }

    public List<Progress> list() {
        return repo.findAll();
    }

    public Progress update(String id, Progress p) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Progress not found");
        p.setId(id);
        return repo.save(p);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
