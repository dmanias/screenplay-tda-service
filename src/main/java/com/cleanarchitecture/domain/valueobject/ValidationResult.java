package com.cleanarchitecture.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ValidationResult implements ValueObject{
    private final boolean potentialValid;      // ◇(A ∧ C) - possibility of AI creative output
    private final boolean foundationValid;     // □(H → ◇C) - necessity of human creative potential
    private final boolean collaborationValid;  // ◇(A ∧ H ∧ C) - possibility of human-AI collaboration
    private final boolean isValid;            // Overall validation result

    // Factory methods for common results
    public static ValidationResult valid() {
        return ValidationResult.builder()
                .potentialValid(true)
                .foundationValid(true)
                .collaborationValid(true)
                .isValid(true)
                .build();
    }

    public static ValidationResult invalid(boolean potential, boolean foundation, boolean collaboration) {
        return ValidationResult.builder()
                .potentialValid(potential)
                .foundationValid(foundation)
                .collaborationValid(collaboration)
                .isValid(false)
                .build();
    }
}
