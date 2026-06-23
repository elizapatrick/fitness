package com.fitness.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgressDTO {
    private String id;
    @NotNull
    private String exerciseId;
    private Double gewicht;
    private Integer wiederholungen;
    private LocalDate datum;
}
