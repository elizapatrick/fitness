package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExerciseDTO {
    private String id;
    @NotBlank
    private String name;
    private String muskelgruppe;
    private String beschreibung;
}
