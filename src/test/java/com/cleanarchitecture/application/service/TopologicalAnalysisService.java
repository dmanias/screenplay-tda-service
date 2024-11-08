package com.cleanarchitecture.application.service;

import com.cleanarchitecture.config.TestConfig;
import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
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
@DisplayName("TopologicalAnalysisService Integration Tests")
class TopologicalAnalysisServiceIntegrationTest {

    @Autowired
    private TopologicalAnalysisService topologicalAnalysisService;

    @Test
    @Transactional
    @DisplayName("Should extract features in Spring context")
    void shouldExtractFeaturesInSpringContext() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        TopologicalFeatures features = topologicalAnalysisService.extractFeatures(screenplay);

        // Assert
        assertAll(
                "Feature Extraction Validation",
                () -> assertThat(features).isNotNull(),
                () -> assertThat(features.getGradientFeatures()).isNotNull().isNotEmpty(),
                () -> assertThat(features.getConnectivityPatterns()).isNotNull().isNotEmpty(),
                () -> assertThat(features.getSpatialStructures()).isNotNull().isNotEmpty(),
                () -> assertThat(features.getCoherenceScore()).isBetween(0.0, 1.0),
                () -> assertThat(features.getComplexityMeasure()).isBetween(0.0, 1.0)
        );
    }

    @Test
    @Transactional
    @DisplayName("Should compute persistence diagram in Spring context")
    void shouldComputePersistenceDiagramInSpringContext() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        PersistenceDiagram diagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

        // Assert
        assertAll(
                "Persistence Diagram Validation",
                () -> assertThat(diagram).isNotNull(),
                () -> assertThat(diagram.getBettiNumbers()).isNotNull().isNotEmpty(),
                () -> assertThat(diagram.getWassersteinDistance()).isBetween(0.0, 1.0),
                () -> assertThat(diagram.getBirthDeathPairs())
                        .isNotNull()
                        .allMatch(pair -> pair.getBirth() <= pair.getDeath())
        );
    }

    private Screenplay createTestScreenplay() {
        Screenplay screenplay = new Screenplay();
        screenplay.setTitle("Integration Test Screenplay");
        screenplay.setScenes(new ArrayList<>());
        screenplay.setCreatedAt(LocalDateTime.now());
        screenplay.setUpdatedAt(LocalDateTime.now());

        Scene scene = new Scene();
        scene.setContent("Test scene content for integration");
        scene.setSequence(1);
        scene.setScreenplay(screenplay);
        screenplay.getScenes().add(scene);

        return screenplay;
    }
}
