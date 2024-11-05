package com.cleanarchitecture.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopologicalFeatures {

    private double[] gradientFeatures;
    private double[] connectivityPatterns;
    private double[] spatialStructures;
    private double coherenceScore;
    private double complexityMeasure;

    @Column(length = 1000)
    private String additionalFeatures; //JSON string for flexible feature storage
}
