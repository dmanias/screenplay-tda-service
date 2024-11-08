package com.cleanarchitecture.infrastructure.persistence.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopologicalFeaturesEmbeddable {
    @Column(name = "gradient_features", columnDefinition = "TEXT")
    private String gradientFeaturesJson;

    @Column(name = "connectivity_patterns", columnDefinition = "TEXT")
    private String connectivityPatternsJson;

    @Column(name = "spatial_structures", columnDefinition = "TEXT")
    private String spatialStructuresJson;

    @Column(name = "coherence_score")
    private double coherenceScore;

    @Column(name = "complexity_measure")
    private double complexityMeasure;

    @Column(name = "additional_features", length = 1000)
    private String additionalFeatures;
}
