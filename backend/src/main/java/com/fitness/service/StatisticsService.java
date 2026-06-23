package com.fitness.service;

import com.fitness.model.Progress;
import com.fitness.repository.ExerciseRepository;
import com.fitness.repository.ProgressRepository;
import com.fitness.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final TrainingRepository trainingRepo;
    private final ExerciseRepository exerciseRepo;
    private final ProgressRepository progressRepo;

    public StatisticsService(TrainingRepository trainingRepo, ExerciseRepository exerciseRepo,
            ProgressRepository progressRepo) {
        this.trainingRepo = trainingRepo;
        this.exerciseRepo = exerciseRepo;
        this.progressRepo = progressRepo;
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> map = new HashMap<>();
        var trainings = trainingRepo.findAll();
        int countTrainings = trainings.size();
        int totalDuration = trainings.stream().mapToInt(t -> t.getDauer() == null ? 0 : t.getDauer()).sum();
        double avgDuration = countTrainings == 0 ? 0 : (double) totalDuration / countTrainings;
        int countExercises = exerciseRepo.findAll().size();

        map.put("countTrainings", countTrainings);
        map.put("totalDuration", totalDuration);
        map.put("avgDuration", avgDuration);
        map.put("countExercises", countExercises);

        List<Progress> progresses = progressRepo.findAll();
        Map<YearMonth, List<Progress>> perMonth = progresses.stream()
                .filter(p -> p.getDatum() != null)
                .collect(Collectors.groupingBy(p -> YearMonth.from(p.getDatum())));

        Map<String, Object> progressPerMonth = new TreeMap<>();
        perMonth.forEach((ym, list) -> {
            progressPerMonth.put(ym.toString(), Map.of(
                    "entries", list.size(),
                    "averageWeight",
                    list.stream().mapToDouble(p -> p.getGewicht() == null ? 0 : p.getGewicht()).average().orElse(0)));
        });

        map.put("progressPerMonth", progressPerMonth);
        return map;
    }

    public Map<String, Object> getProgressChartData() {
        List<Progress> progresses = progressRepo.findAll();
        List<Map<String, Object>> weightSeries = progresses.stream()
                .sorted(Comparator.comparing(Progress::getDatum))
                .map(p -> Map.of("date", p.getDatum(), "weight", p.getGewicht(), "exerciseId", p.getExerciseId()))
                .collect(Collectors.toList());

        List<Map<String, Object>> repSeries = progresses.stream()
                .sorted(Comparator.comparing(Progress::getDatum))
                .map(p -> Map.of("date", p.getDatum(), "reps", p.getWiederholungen(), "exerciseId", p.getExerciseId()))
                .collect(Collectors.toList());

        return Map.of("weightSeries", weightSeries, "repSeries", repSeries);
    }
}
