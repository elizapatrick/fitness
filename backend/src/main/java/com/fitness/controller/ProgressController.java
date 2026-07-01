package com.fitness.controller;

import com.fitness.dto.ProgressDTO;
import com.fitness.model.Progress;
import com.fitness.service.ProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
public class ProgressController {
    private final ProgressService service;

    public ProgressController(ProgressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Progress> create(@Valid @RequestBody ProgressDTO dto) {
        Progress p = new Progress();
        p.setExerciseId(dto.getExerciseId());
        p.setGewicht(dto.getGewicht());
        p.setWiederholungen(dto.getWiederholungen());
        p.setDatum(dto.getDatum());
        Progress created = service.create(p);
        return ResponseEntity.created(URI.create("/api/progress/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Progress> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public Progress update(@PathVariable String id, @Valid @RequestBody ProgressDTO dto) {
        Progress p = new Progress();
        p.setExerciseId(dto.getExerciseId());
        p.setGewicht(dto.getGewicht());
        p.setWiederholungen(dto.getWiederholungen());
        p.setDatum(dto.getDatum());
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
