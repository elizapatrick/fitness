package com.fitness.controller;

import com.fitness.dto.PlanDTO;
import com.fitness.model.TrainingPlan;
import com.fitness.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrainingPlan> create(@Valid @RequestBody PlanDTO dto) {
        TrainingPlan p = new TrainingPlan();
        p.setName(dto.getName());
        p.setBeschreibung(dto.getBeschreibung());
        p.setTrainingIds(dto.getTrainingIds());
        TrainingPlan created = service.create(p);
        return ResponseEntity.created(URI.create("/api/plans/" + created.getId())).body(created);
    }

    @GetMapping
    public List<TrainingPlan> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public TrainingPlan update(@PathVariable String id, @Valid @RequestBody PlanDTO dto) {
        TrainingPlan p = new TrainingPlan();
        p.setName(dto.getName());
        p.setBeschreibung(dto.getBeschreibung());
        p.setTrainingIds(dto.getTrainingIds());
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
