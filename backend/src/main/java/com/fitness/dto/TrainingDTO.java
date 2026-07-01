package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TrainingDTO {
    private String id;
    @NotNull
    private String planId;
    @NotNull
    private String datum;
    private Integer dauerMinuten;
    private String notizen;
    private List<String> uebungIds;
}
