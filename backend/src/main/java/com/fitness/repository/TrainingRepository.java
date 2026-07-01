package com.fitness.repository;

import com.fitness.model.Training;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrainingRepository extends MongoRepository<Training, String> {
    List<Training> findByPlanId(String planId);
}
