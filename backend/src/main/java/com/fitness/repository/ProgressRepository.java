package com.fitness.repository;

import com.fitness.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProgressRepository extends MongoRepository<Progress, String> {
    List<Progress> findByUebungsId(String uebungsId);

    List<Progress> findByDatumBetween(String from, String to);
}
