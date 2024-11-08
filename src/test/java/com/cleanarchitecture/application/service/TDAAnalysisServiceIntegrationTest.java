package com.cleanarchitecture.application.service;

import com.cleanarchitecture.config.TestConfig;
import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
@DisplayName("TDAAnalysisService Integration Tests")
class TDAAnalysisServiceIntegrationTest {

    @Autowired
    private TDAAnalysisService tdaAnalysisService;

    @Test
    @Transactional
    @DisplayName("Should perform complete TDA analysis in Spring context")
    void shouldPerformCompleteTDAAnalysisInSpringContext() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        ScreenplayMetrics metrics = tdaAnalysisService.analyzeScreenplay(screenplay);

        // Assert
        assertAll(
                "Complete TDA Analysis Validation",
                () -> assertThat(metrics).isNotNull(),
                () -> assertThat(metrics.getScreenplay()).isEqualTo(screenplay),
                () -> assertThat(metrics.getTopologicalFeatures()).isNotNull(),
                () -> assertThat(metrics.getPersistenceDiagram()).isNotNull(),
                () -> assertThat(metrics.getPatternMetrics()).isNotNull(),
                () -> assertThat(screenplay.getScreenplayMetrics()).isEqualTo(metrics)
        );

        // Validate TopologicalFeatures
        assertAll(
                "Topological Features Validation",
                () -> assertThat(metrics.getTopologicalFeatures().getGradientFeatures()).isNotEmpty(),
                () -> assertThat(metrics.getTopologicalFeatures().getCoherenceScore()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getTopologicalFeatures().getComplexityMeasure()).isBetween(0.0, 1.0)
        );

        // Validate PersistenceDiagram
        assertAll(
                "Persistence Diagram Validation",
                () -> assertThat(metrics.getPersistenceDiagram().getBettiNumbers()).isNotEmpty(),
                () -> assertThat(metrics.getPersistenceDiagram().getWassersteinDistance()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getPersistenceDiagram().getScreenplayMetrics()).isEqualTo(metrics)
        );

        // Validate PatternMetrics
        assertAll(
                "Pattern Metrics Validation",
                () -> assertThat(metrics.getPatternMetrics().getHumanPatternScore()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getPatternMetrics().getAiPatternScore()).isBetween(0.0, 1.0),
                () -> assertThat(metrics.getPatternMetrics().getHybridScore()).isBetween(0.0, 1.0)
        );
    }

    @Test
    @Transactional
    @DisplayName("Should maintain correct relationships in analysis results")
    void shouldMaintainCorrectRelationshipsInAnalysisResults() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        ScreenplayMetrics metrics = tdaAnalysisService.analyzeScreenplay(screenplay);

        // Assert
        assertAll(
                "Relationship Validation",
                () -> assertThat(metrics.getScreenplay()).isSameAs(screenplay),
                () -> assertThat(screenplay.getScreenplayMetrics()).isSameAs(metrics),
                () -> assertThat(metrics.getPersistenceDiagram().getScreenplayMetrics()).isSameAs(metrics)
        );
    }

    private Screenplay createTestScreenplay() {
        Screenplay screenplay = new Screenplay();
        screenplay.setTitle("Integration Test Screenplay");
        screenplay.setScenes(new ArrayList<>());
        screenplay.setCreatedAt(LocalDateTime.now());
        screenplay.setUpdatedAt(LocalDateTime.now());

        // Add a test scene
        Scene scene = new Scene();
        scene.setContent("Test scene content for integration testing");
        scene.setSequence(1);
        scene.setScreenplay(screenplay);
        screenplay.getScenes().add(scene);

        // Add some dialogue to the scene
        Dialogue dialogue = new Dialogue();
        dialogue.setCharacterName("Test Character");
        dialogue.setContent("Test dialogue content");
        dialogue.setScene(scene);
        scene.getDialogues().add(dialogue);

        return screenplay;
    }
}
