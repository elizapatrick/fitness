package com.fitness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Document(collection = "trainings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    private String id;
    private String planId;
    private String datum;
    private int dauer_minuten;
    private String notizen;
    private List<String> uebungIds;
}