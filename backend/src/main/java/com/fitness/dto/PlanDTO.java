package com.fitness.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlanDTO {
    private String id;
    @NotNull
    private String benutzerId;
    @NotBlank
    private String name;
    private String ziel;
    private Integer wochenDauer;
}
