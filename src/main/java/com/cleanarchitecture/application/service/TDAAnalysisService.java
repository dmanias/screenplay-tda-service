package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.port.input.TDAAnalysisPort;
import com.cleanarchitecture.domain.validation.BaseValidator;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.domain.valueobject.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TDAAnalysisService implements TDAAnalysisPort {

    private final BaseValidator<Screenplay> baseValidator;
    private final ModalLogicService modalLogicService;
    private final TopologicalAnalysisService topologicalAnalysisService;
    private final PatternAnalysisService patternAnalysisService;

    @Override
    public ScreenplayMetrics analyzeScreenplay(Screenplay screenplay) {
        // Initial validation
        baseValidator.validate(screenplay);

        // 1. Modal Logic Framework validation (Farooq Khan et al., 2024)
        ValidationResult validationResult = modalLogicService.validateCreativeProcess(screenplay);
        if (!validationResult.isValid()) {
            handleInvalidCreativeProcess(validationResult);
        }

        // 2. Topological Analysis (Kushnareva et al., 2021)
        TopologicalFeatures features = topologicalAnalysisService.extractFeatures(screenplay);
        PersistenceDiagram persistenceDiagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

        // 3. Pattern Analysis (Papia et al., 2023)
        PatternMetrics patternMetrics = patternAnalysisService.analyzePatterns(features, persistenceDiagram);

        // 4. Construct and return metrics
        return createScreenplayMetrics(screenplay, features, persistenceDiagram, patternMetrics);
    }

    private ScreenplayMetrics createScreenplayMetrics(
            Screenplay screenplay,
            TopologicalFeatures features,
            PersistenceDiagram persistenceDiagram,
            PatternMetrics patternMetrics) {

        ScreenplayMetrics metrics = new ScreenplayMetrics();
        metrics.setScreenplay(screenplay);
        metrics.setTopologicalFeatures(features);
        metrics.setPersistenceDiagram(persistenceDiagram);
        metrics.setPatternMetrics(patternMetrics);

        // Set up bidirectional relationships using correct method names
        screenplay.setMetrics(metrics);
        persistenceDiagram.setScreenplayMetrics(metrics);

        return metrics;
    }

    private void handleInvalidCreativeProcess(ValidationResult result) {
        StringBuilder errorMessage = new StringBuilder("Creative process validation failed: ");

        if (!result.potentialValid()) {
            errorMessage.append("Insufficient AI creative potential. ");
        }
        if (!result.foundationValid()) {
            errorMessage.append("Insufficient human foundation. ");
        }
        if (!result.collaborationValid()) {
            errorMessage.append("Insufficient collaboration score. ");
        }

        throw new IllegalStateException(errorMessage.toString());
    }
}