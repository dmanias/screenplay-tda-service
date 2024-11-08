package com.cleanarchitecture.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @param potentialValid     ◇(A ∧ C) - possibility of AI creative output
 * @param foundationValid    □(H → ◇C) - necessity of human creative potential
 * @param collaborationValid ◇(A ∧ H ∧ C) - possibility of human-AI collaboration
 * @param isValid            Overall validation result
 */

@Builder
public record ValidationResult(boolean potentialValid, boolean foundationValid, boolean collaborationValid,
                               boolean isValid) implements ValueObject {
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
