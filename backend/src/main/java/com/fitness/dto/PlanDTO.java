package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PlanDTO {
    private String id;
    @NotBlank
    private String name;
    private String beschreibung;
    private List<String> trainingIds;
}
