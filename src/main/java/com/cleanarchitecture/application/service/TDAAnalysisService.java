package com.cleanarchitecture.application.service;


import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.port.input.TDAAnalysisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TDAAnalysisService implements TDAAnalysisPort {

    private final ModalLogicService modalLogicService;
    private final TopologicalAnalysisService topologicalAnalysisService;
    private final PatternAnalysisService patternAnalysisService;

    @Override
    public ScreenplayMetrics analyzeScreenplay(Screenplay screenplay) {
        // 1. Modal Logic Framework validation
        var validationResult = modalLogicService.validateCreativeProcess(screenplay);

        // 2. Topological Analysis
        var features = topologicalAnalysisService.extractFeatures(screenplay);
        var persistenceDiagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

        // 3. Pattern Analysis
        var patternMetrics = patternAnalysisService.analyzePatterns(features, persistenceDiagram);

        // 4. Create and populate ScreenplayMetrics
        ScreenplayMetrics metrics = new ScreenplayMetrics();
        metrics.setScreenplay(screenplay);
        metrics.setTopologicalFeatures(features);
        metrics.setPersistenceDiagram(persistenceDiagram);
        metrics.setPatternMetrics(patternMetrics);

        // Set up bidirectional relationship
        screenplay.setScreenplayMetrics(metrics);
        persistenceDiagram.setScreenplayMetrics(metrics);

        return metrics;
    }
}