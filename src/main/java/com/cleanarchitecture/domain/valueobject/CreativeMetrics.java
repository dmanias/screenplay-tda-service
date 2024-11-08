package com.cleanarchitecture.domain.valueobject;

public record CreativeMetrics(
        double aiCreativePotential,
        double humanFoundation,
        double collaborationScore
) implements ValueObject {
    // Compact constructor for validation
    public CreativeMetrics {
        if (aiCreativePotential < 0 || aiCreativePotential > 1) {
            throw new IllegalArgumentException("AI potential must be between 0 and 1");
        }
        if (humanFoundation < 0 || humanFoundation > 1) {
            throw new IllegalArgumentException("Human foundation must be between 0 and 1");
        }
        if (collaborationScore < 0 || collaborationScore > 1) {
            throw new IllegalArgumentException("Collaboration score must be between 0 and 1");
        }
    }

    public boolean isValidAIContribution() {
        return aiCreativePotential >= 0.5;
    }

    public boolean isValidHumanContribution() {
        return humanFoundation >= 0.4;
    }

    public boolean hasEffectiveCollaboration() {
        return collaborationScore >= 0.7;
    }
}