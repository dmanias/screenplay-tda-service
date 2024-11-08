package com.cleanarchitecture.domain.valueobject;

import lombok.Value;

public record PatternMetrics(
        double humanPatternScore,
        double aiPatternScore,
        double hybridScore
) implements ValueObject {
    // Compact constructor for validation
    public PatternMetrics {
        if (humanPatternScore < 0 || humanPatternScore > 1) {
            throw new IllegalArgumentException("Human pattern score must be between 0 and 1");
        }
        if (aiPatternScore < 0 || aiPatternScore > 1) {
            throw new IllegalArgumentException("AI pattern score must be between 0 and 1");
        }
        if (hybridScore < 0 || hybridScore > 1) {
            throw new IllegalArgumentException("Hybrid score must be between 0 and 1");
        }
    }

    public boolean isBalanced() {
        return Math.abs(humanPatternScore - aiPatternScore) < 0.3;
    }

    public boolean hasStrongHumanInfluence() {
        return humanPatternScore > 0.7;
    }

    public boolean hasStrongAIInfluence() {
        return aiPatternScore > 0.7;
    }
}