package com.fitness.repository;

import com.fitness.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProgressRepository extends MongoRepository<Progress, String> {
    List<Progress> findByExerciseId(String exerciseId);

    List<Progress> findByDatumBetween(LocalDate from, LocalDate to);
}
