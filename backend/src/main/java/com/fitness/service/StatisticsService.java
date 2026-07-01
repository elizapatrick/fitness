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
                int totalDuration = trainings.stream().mapToInt(t -> t.getDauer_minuten()).sum();
                double avgDuration = countTrainings == 0 ? 0 : (double) totalDuration / countTrainings;
                int countExercises = exerciseRepo.findAll().size();

                map.put("countTrainings", countTrainings);
                map.put("totalDuration", totalDuration);
                map.put("avgDuration", avgDuration);
                map.put("countExercises", countExercises);

                List<Progress> progresses = progressRepo.findAll();
                Map<YearMonth, List<Progress>> perMonth = progresses.stream()
                                .map(this::attachParsedDate)
                                .filter(p -> p.date() != null)
                                .collect(Collectors.groupingBy(StatisticsEntry::dateMonth,
                                                Collectors.mapping(StatisticsEntry::progress, Collectors.toList())));

                Map<String, Object> progressPerMonth = new TreeMap<>();
                perMonth.forEach((ym, list) -> {
                        progressPerMonth.put(ym.toString(), Map.<String, Object>of(
                                        "entries", list.size(),
                                        "averageWeight",
                                        list.stream().mapToDouble(Progress::getWert).average().orElse(0)));
                });

                map.put("progressPerMonth", progressPerMonth);
                return map;
        }

        public Map<String, Object> getProgressChartData() {
                List<Progress> progresses = progressRepo.findAll();

                // Absicherung: Filtert alle Einträge heraus, bei denen das Datum null ist, um
                // Abstürze zu verhindern
                List<Map<String, Object>> weightSeries = progresses.stream()
                                .map(this::attachParsedDate)
                                .filter(p -> p.date() != null)
                                .sorted(Comparator.comparing(StatisticsEntry::date))
                                .map(p -> Map.<String, Object>of(
                                                "date", p.progress().getDatum(),
                                                "weight", p.progress().getWert(),
                                                "exerciseId",
                                                p.progress().getUebungsId() != null ? p.progress().getUebungsId() : ""))
                                .collect(Collectors.toList());

                List<Map<String, Object>> repSeries = progresses.stream()
                                .map(this::attachParsedDate)
                                .filter(p -> p.date() != null)
                                .sorted(Comparator.comparing(StatisticsEntry::date))
                                .map(p -> Map.<String, Object>of(
                                                "date", p.progress().getDatum(),
                                                "reps",
                                                p.progress().getEinheit() != null ? p.progress().getEinheit() : "",
                                                "exerciseId",
                                                p.progress().getUebungsId() != null ? p.progress().getUebungsId() : ""))
                                .collect(Collectors.toList());

                return Map.of("weightSeries", weightSeries, "repSeries", repSeries);
        }

        private StatisticsEntry attachParsedDate(Progress progress) {
                if (progress == null || progress.getDatum() == null || progress.getDatum().isBlank()) {
                        return new StatisticsEntry(progress, null);
                }

                try {
                        return new StatisticsEntry(progress, YearMonth.from(LocalDate.parse(progress.getDatum())));
                } catch (Exception ex) {
                        return new StatisticsEntry(progress, null);
                }
        }

        private record StatisticsEntry(Progress progress, YearMonth date) {
                private YearMonth dateMonth() {
                        return date;
                }
        }
}