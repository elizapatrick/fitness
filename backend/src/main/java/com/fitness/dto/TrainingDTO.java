package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TrainingDTO {
    private String id;
    @NotBlank
    private String titel;
    @NotNull
    private LocalDate datum;
    private Integer dauer;
    private String trainingsart;
    private List<String> exerciseIds;
}
