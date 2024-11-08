package com.cleanarchitecture.domain.valueobject;

public record TopologicalFeatures(
        double[] gradientFeatures,
        double[] connectivityPatterns,
        double[] spatialStructures,
        double coherenceScore,
        double complexityMeasure,
        String additionalFeatures
) implements ValueObject {
    // Compact constructor for validation
    public TopologicalFeatures {
        if (gradientFeatures == null || gradientFeatures.length == 0) {
            throw new IllegalArgumentException("Gradient features cannot be empty");
        }
        if (connectivityPatterns == null || connectivityPatterns.length == 0) {
            throw new IllegalArgumentException("Connectivity patterns cannot be empty");
        }
        if (spatialStructures == null || spatialStructures.length == 0) {
            throw new IllegalArgumentException("Spatial structures cannot be empty");
        }
        if (coherenceScore < 0 || coherenceScore > 1) {
            throw new IllegalArgumentException("Coherence score must be between 0 and 1");
        }
        if (complexityMeasure < 0) {
            throw new IllegalArgumentException("Complexity measure cannot be negative");
        }

        // Defensive copying of arrays because arrays are mutable
        gradientFeatures = gradientFeatures.clone();
        connectivityPatterns = connectivityPatterns.clone();
        spatialStructures = spatialStructures.clone();
    }

    // Return defensive copies of arrays to maintain immutability
    public double[] gradientFeatures() {
        return gradientFeatures.clone();
    }

    public double[] connectivityPatterns() {
        return connectivityPatterns.clone();
    }

    public double[] spatialStructures() {
        return spatialStructures.clone();
    }

    public boolean hasAcceptableCoherence() {
        return coherenceScore > 0.5;
    }

    public boolean hasHighComplexity() {
        return complexityMeasure > 0.7;
    }
}

