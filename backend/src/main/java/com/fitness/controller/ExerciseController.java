package com.fitness.controller;

import com.fitness.dto.ExerciseDTO;
import com.fitness.model.Exercise;
import com.fitness.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "*")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@Valid @RequestBody ExerciseDTO dto) {
        Exercise e = new Exercise();
        e.setName(dto.getName());
        e.setMuskelgruppe(dto.getMuskelgruppe());
        e.setBeschreibung(dto.getBeschreibung());
        Exercise created = service.create(e);
        return ResponseEntity.created(URI.create("/api/exercises/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Exercise> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public Exercise update(@PathVariable String id, @Valid @RequestBody ExerciseDTO dto) {
        Exercise e = new Exercise();
        e.setName(dto.getName());
        e.setMuskelgruppe(dto.getMuskelgruppe());
        e.setBeschreibung(dto.getBeschreibung());
        return service.update(id, e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
