package com.fitness.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressDTO {
    private String id;
    @NotNull
    private String benutzerId;
    @NotNull
    private String uebungsId;
    @NotNull
    private String datum;
    private double wert;
    private String einheit;
    private String ziel;
}
