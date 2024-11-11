package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.domain.port.input.PatternAnalysisPort;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatternAnalysisService implements PatternAnalysisPort {

    @Override
    public PatternMetrics analyzePatterns(TopologicalFeatures features, PersistenceDiagram diagram) {
        // Based on Papia et al. (2023) findings for pattern analysis
        double humanScore = calculateHumanPatternScore(features);
        double aiScore = calculateAIPatternScore(features, diagram);
        double hybridScore = calculateCollaborativeScore(humanScore, aiScore);

        return new PatternMetrics(
                normalizeScore(humanScore),
                normalizeScore(aiScore),
                normalizeScore(hybridScore)
        );
    }

    private double calculateHumanPatternScore(TopologicalFeatures features) {
        // Following Farooq Khan et al. (2024) pattern distinctions
        double complexityFactor = features.complexityMeasure();
        double coherenceFactor = features.coherenceScore();

        // Human patterns show higher topological complexity
        return (complexityFactor * 0.6) + (coherenceFactor * 0.4);
    }

    private double calculateAIPatternScore(TopologicalFeatures features, PersistenceDiagram diagram) {
        // Based on Kushnareva et al. (2021) findings
        double bettiNumberFactor = calculateBettiNumberFactor(diagram.getBettiNumbers());
        double wassersteinFactor = 1.0 - (diagram.getWassersteinDistance() / 1.0);

        return (bettiNumberFactor * 0.5) + (wassersteinFactor * 0.5);
    }

    private double calculateBettiNumberFactor(List<Double> bettiNumbers) {
        // Normalize Betti numbers based on theoretical thresholds
        return bettiNumbers.stream()
                .mapToDouble(b -> b / 3.42) // Normalized against human baseline from research
                .average()
                .orElse(0.0);
    }

    private double calculateCollaborativeScore(double humanScore, double aiScore) {
        // Geometric mean for balanced hybrid scoring
        return Math.sqrt(humanScore * aiScore);
    }

    private double normalizeScore(double score) {
        return Math.min(1.0, Math.max(0.0, score));
    }
}