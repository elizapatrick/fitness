package com.fitness.controller;

import com.fitness.dto.TrainingDTO;
import com.fitness.model.Training;
import com.fitness.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/trainings")
@CrossOrigin(origins = "*")
public class TrainingController {
    private final TrainingService service;

    public TrainingController(TrainingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Training> create(@Valid @RequestBody TrainingDTO dto) {
        Training t = new Training();
        t.setPlanId(dto.getPlanId());
        t.setDatum(dto.getDatum());
        t.setDauer_minuten(dto.getDauerMinuten() == null ? 0 : dto.getDauerMinuten());
        t.setNotizen(dto.getNotizen());
        t.setUebungIds(dto.getUebungIds());
        Training created = service.create(t);
        return ResponseEntity.created(URI.create("/api/trainings/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Training> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Training get(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Training update(@PathVariable String id, @Valid @RequestBody TrainingDTO dto) {
        Training t = new Training();
        t.setPlanId(dto.getPlanId());
        t.setDatum(dto.getDatum());
        t.setDauer_minuten(dto.getDauerMinuten() == null ? 0 : dto.getDauerMinuten());
        t.setNotizen(dto.getNotizen());
        t.setUebungIds(dto.getUebungIds());
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<Training> filter(@RequestParam(required = false) String planId,
            @RequestParam(required = false) String type) {
        String resolvedPlanId = planId != null ? planId : type;
        return resolvedPlanId == null ? service.list() : service.filterByPlanId(resolvedPlanId);
    }
}
