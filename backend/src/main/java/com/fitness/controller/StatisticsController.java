package com.fitness.controller;

import com.fitness.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatisticsController {
    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/statistics")
    public Map<String, Object> stats() {
        return service.getStatistics();
    }

    @GetMapping("/progress/chart")
    public Map<String, Object> chart() {
        return service.getProgressChartData();
    }
}
