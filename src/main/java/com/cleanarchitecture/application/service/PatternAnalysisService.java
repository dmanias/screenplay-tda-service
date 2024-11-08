package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.domain.port.input.PatternAnalysisPort;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class PatternAnalysisService implements PatternAnalysisPort {

    private static final double MAX_WASSERSTEIN_DISTANCE = 1.0;

    @Override
    public PatternMetrics analyzePatterns(TopologicalFeatures features, PersistenceDiagram diagram) {
        double humanScore = calculateHumanPatternScore(features);
        double aiScore = calculateAIPatternScore(features, diagram);
        double hybridScore = calculateHybridScore(humanScore, aiScore);

        return PatternMetrics.builder()
                .humanPatternScore(humanScore)
                .aiPatternScore(aiScore)
                .hybridScore(hybridScore)
                .build();
    }

    private double calculateHumanPatternScore(TopologicalFeatures features) {
        // Human patterns typically show higher complexity and variation
        double complexity = features.getComplexityMeasure();
        double coherence = features.getCoherenceScore();
        double variation = calculateVariation(features.getGradientFeatures());

        // Weight the factors
        return (complexity * 0.4 + coherence * 0.3 + variation * 0.3);
    }

    private double calculateAIPatternScore(TopologicalFeatures features, PersistenceDiagram diagram) {
        // AI patterns typically show more regularity and lower Wasserstein distance
        double regularity = 1.0 - calculateVariation(features.getConnectivityPatterns());
        double wassersteinFactor = 1.0 - (diagram.getWassersteinDistance() / MAX_WASSERSTEIN_DISTANCE);

        // Weight the factors
        return (regularity * 0.6 + wassersteinFactor * 0.4);
    }

    private double calculateHybridScore(double humanScore, double aiScore) {
        // Geometric mean for balanced scoring
        return Math.sqrt(humanScore * aiScore);
    }

    private double calculateVariation(double[] features) {
        if (features == null || features.length == 0) {
            return 0.0;
        }
        double mean = Arrays.stream(features).average().orElse(0.0);
        double variance = Arrays.stream(features)
                .map(f -> Math.pow(f - mean, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variance);
    }

    private double normalizeScore(double score) {
        return Math.min(1.0, Math.max(0.0, score));
    }
}