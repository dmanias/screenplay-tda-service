package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
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
        CreativeMetrics metrics = screenplay.getMetrics().getCreativeMetrics();

        // 1. Creative Potential: ◇(A ∧ C)
        // Validates the possibility of AI creative output
        boolean potentialValid = metrics.isValidAIContribution();

        // 2. Human Foundation: □(H → ◇C)
        // Validates the necessity of human creative potential
        boolean foundationValid = metrics.isValidHumanContribution();

        // 3. Collaborative Creation: ◇(A ∧ H ∧ C)
        // Validates the possibility of human-AI collaboration
        boolean collaborationValid = metrics.hasEffectiveCollaboration();

        return potentialValid && foundationValid && collaborationValid ?
                ValidationResult.valid() :
                ValidationResult.invalid(potentialValid, foundationValid, collaborationValid);
    }
}