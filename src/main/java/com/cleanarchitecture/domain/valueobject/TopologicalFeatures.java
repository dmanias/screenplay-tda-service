package com.cleanarchitecture.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopologicalFeatures {

    private double[] gradientFeatures;
    private double[] connectivityPatterns;
    private double[] spatialStructures;
    private double coherenceScore;
    private double complexityMeasure;

    @Column(length = 1000)
    private String additionalFeatures; //JSON string for flexible feature storage

    // Static factory method for ease of creation
    public static TopologicalFeatures create(
            double[] gradientFeatures,
            double[] connectivityPatterns,
            double[] spatialStructures,
            double coherenceScore,
            double complexityMeasure) {

        TopologicalFeatures features = new TopologicalFeatures();
        features.setGradientFeatures(gradientFeatures);
        features.setConnectivityPatterns(connectivityPatterns);
        features.setSpatialStructures(spatialStructures);
        features.setCoherenceScore(coherenceScore);
        features.setComplexityMeasure(complexityMeasure);
        features.setAdditionalFeatures("{}");
        return features;
    }


}
