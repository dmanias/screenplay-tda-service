package com.cleanarchitecture.application.dto;

import lombok.Data;

@Data
public class TopologicalFeaturesDTO {
    private double[] gradientFeatures;
    private double[] connectivityPatterns;
    private double[] spatialStructures;
    private double coherenceScore;
    private double complexityMeasure;
    private String additionalFeatures;
}