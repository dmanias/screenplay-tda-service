package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.CreativeMetrics;
import com.cleanarchitecture.domain.port.input.ModalLogicPort;
import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.valueobject.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModalLogicService implements ModalLogicPort {

    @Override
    public ValidationResult validateCreativeProcess(Screenplay screenplay) {
        CreativeMetrics metrics = screenplay.getScreenplayMetrics().getCreativeMetrics();

        // Validates three modal logic axioms:
        // 1. Creative Potential: ◇(A ∧ C)
        boolean potentialValid = validateCreativePotential(metrics);

        // 2. Human Foundation: □(H → ◇C)
        boolean foundationValid = validateHumanFoundation(metrics);

        // 3. Collaborative Creation: ◇(A ∧ H ∧ C)
        boolean collaborationValid = validateCollaboration(metrics);

        if (potentialValid && foundationValid && collaborationValid) {
            return ValidationResult.valid();
        } else {
            return ValidationResult.invalid(potentialValid, foundationValid, collaborationValid);
        }
    }

    private boolean validateCreativePotential(CreativeMetrics metrics) {
        return metrics.getAiCreativePotential() > 0.5;
    }

    private boolean validateHumanFoundation(CreativeMetrics metrics) {
        return metrics.getHumanFoundation() > 0.4;
    }

    private boolean validateCollaboration(CreativeMetrics metrics) {
        return metrics.getCollaborationScore() > 0.7;
    }
}