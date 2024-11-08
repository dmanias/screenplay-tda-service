package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PatternAnalysisServiceUnitTest {

    private PatternAnalysisService patternAnalysisService;
    private TopologicalFeatures features;
    private PersistenceDiagram diagram;

    @BeforeEach
    void setUp() {
        patternAnalysisService = new PatternAnalysisService();

        // Create test data
        features = TopologicalFeatures.create(
                new double[]{0.5, 0.6, 0.7},  // gradientFeatures
                new double[]{0.4, 0.5, 0.6},  // connectivityPatterns
                new double[]{0.3, 0.4, 0.5},  // spatialStructures
                0.75,                         // coherenceScore
                0.65                          // complexityMeasure
        );

        diagram = new PersistenceDiagram();
    }

    @ParameterizedTest(name = "Wasserstein distance: {0}, Expected hybrid score > {1}")
    @CsvSource({
            "0.1, 0.8",
            "0.5, 0.5",
            "0.9, 0.2"
    })
    void shouldCalculateAppropriateHybridScores(double distance, double expectedMinScore) {
        // Arrange
        diagram.setWassersteinDistance(distance);

        // Act
        PatternMetrics metrics = patternAnalysisService.analyzePatterns(features, diagram);

        // Assert
        assertNotNull(metrics);
        assertTrue(metrics.getHybridScore() > expectedMinScore,
                String.format("Expected hybrid score > %f but was %f",
                        expectedMinScore, metrics.getHybridScore()));

        // Additional assertions
        assertTrue(metrics.getHumanPatternScore() >= 0 && metrics.getHumanPatternScore() <= 1,
                "Human pattern score should be between 0 and 1");
        assertTrue(metrics.getAiPatternScore() >= 0 && metrics.getAiPatternScore() <= 1,
                "AI pattern score should be between 0 and 1");
    }
    @Test
    void shouldHandleEmptyFeatures() {
        // Arrange
        features = TopologicalFeatures.create(
                new double[]{},
                new double[]{},
                new double[]{},
                0.5,
                0.5
        );

        // Act
        PatternMetrics metrics = patternAnalysisService.analyzePatterns(features, diagram);

        // Assert
        assertNotNull(metrics);
        assertTrue(metrics.getHumanPatternScore() >= 0 && metrics.getHumanPatternScore() <= 1);
        assertTrue(metrics.getAiPatternScore() >= 0 && metrics.getAiPatternScore() <= 1);
    }
}