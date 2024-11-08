package com.cleanarchitecture.domain.valueobject;

public record ValidationResult(
        boolean potentialValid,
        boolean foundationValid,
        boolean collaborationValid,
        boolean isValid
) implements ValueObject {

    // Static factory methods
    public static ValidationResult valid() {
        return new ValidationResult(true, true, true, true);
    }

    public static ValidationResult invalid(boolean potential, boolean foundation, boolean collaboration) {
        return new ValidationResult(potential, foundation, collaboration, false);
    }

    public boolean needsHumanIntervention() {
        return !foundationValid || !collaborationValid;
    }

    public boolean needsAITuning() {
        return !potentialValid;
    }
}
