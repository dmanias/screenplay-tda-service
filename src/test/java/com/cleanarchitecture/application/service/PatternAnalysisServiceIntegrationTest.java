package com.cleanarchitecture.application.service;

import com.cleanarchitecture.config.TestConfig;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
@DisplayName("PatternAnalysisService Integration Tests")
class PatternAnalysisServiceIntegrationTest {

    @Autowired
    private PatternAnalysisService patternAnalysisService;

    @Test
    @DisplayName("Should analyze patterns in Spring context")
    void shouldAnalyzePatternsInSpringContext() {
        // Arrange
        TopologicalFeatures features = createTestFeatures();
        PersistenceDiagram diagram = createTestDiagram();

        // Act
        PatternMetrics metrics = patternAnalysisService.analyzePatterns(features, diagram);

        // Assert
        assertAll(
                "Pattern Analysis Integration",
                () -> assertThat(metrics).isNotNull(),
                () -> assertThat(metrics.getHumanPatternScore()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getAiPatternScore()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getHybridScore()).isBetween(0.0, 1.0)
        );
    }

    private TopologicalFeatures createTestFeatures() {
        return TopologicalFeatures.builder()
                .gradientFeatures(new double[]{0.5, 0.6, 0.7})
                .connectivityPatterns(new double[]{0.4, 0.5, 0.6})
                .spatialStructures(new double[]{0.3, 0.4, 0.5})
                .coherenceScore(0.75)
                .complexityMeasure(0.65)
                .additionalFeatures("{}")
                .build();
    }

    private PersistenceDiagram createTestDiagram() {
        return PersistenceDiagram.builder()
                .bettiNumbers(java.util.Arrays.asList(1.0, 2.0, 1.0))
                .wassersteinDistance(0.5)
                .build();
    }
}