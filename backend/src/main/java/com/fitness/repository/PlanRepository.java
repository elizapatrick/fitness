package com.fitness.repository;

import com.fitness.model.TrainingPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepository extends MongoRepository<TrainingPlan, String> {
}
