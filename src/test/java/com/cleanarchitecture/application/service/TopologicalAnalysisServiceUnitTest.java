package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("TopologicalAnalysisService Unit Tests")
class TopologicalAnalysisServiceUnitTest {

    @Mock
    private FeatureExtractionPort featureExtractionPort;

    private TopologicalAnalysisService topologicalAnalysisService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        topologicalAnalysisService = new TopologicalAnalysisService(featureExtractionPort);
        setupMocks();
    }

    private void setupMocks() {
        when(featureExtractionPort.extractGradientFeatures(any()))
                .thenReturn(new double[]{0.5, 0.6, 0.7});
        when(featureExtractionPort.extractConnectivityPatterns(any()))
                .thenReturn(new double[]{0.4, 0.5, 0.6});
        when(featureExtractionPort.extractSpatialStructures(any()))
                .thenReturn(new double[]{0.3, 0.4, 0.5});
    }

    @Nested
    @DisplayName("Feature Extraction Tests")
    class FeatureExtractionTests {

        @Test
        @DisplayName("Should extract features successfully")
        void shouldExtractFeatures() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();

            // Act
            TopologicalFeatures features = topologicalAnalysisService.extractFeatures(screenplay);

            // Assert
            assertThat(features).isNotNull();
            assertThat(features.getGradientFeatures()).containsExactly(0.5, 0.6, 0.7);
            assertThat(features.getConnectivityPatterns()).containsExactly(0.4, 0.5, 0.6);
            assertThat(features.getSpatialStructures()).containsExactly(0.3, 0.4, 0.5);
            assertThat(features.getCoherenceScore()).isBetween(0.0, 1.0);
            assertThat(features.getComplexityMeasure()).isBetween(0.0, 1.0);
        }
    }

    @Nested
    @DisplayName("Persistence Diagram Tests")
    class PersistenceDiagramTests {

        @Test
        @DisplayName("Should compute persistence diagram")
        void shouldComputePersistenceDiagram() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();

            // Act
            PersistenceDiagram diagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

            // Assert
            assertThat(diagram).isNotNull();
            assertThat(diagram.getBettiNumbers()).isNotEmpty();
            assertThat(diagram.getWassersteinDistance()).isBetween(0.0, 1.0);
            assertThat(diagram.getBirthDeathPairs()).isNotNull();
        }

        @Test
        @DisplayName("Should compute correct Betti numbers")
        void shouldComputeCorrectBettiNumbers() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();

            // Act
            PersistenceDiagram diagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

            // Assert
            List<Double> bettiNumbers = diagram.getBettiNumbers();
            assertThat(bettiNumbers).hasSize(3); // Assuming we expect 3 Betti numbers
            assertThat(bettiNumbers)
                    .allMatch(number -> number >= 0.0)
                    .allMatch(number -> number <= 10.0); // Reasonable upper bound for Betti numbers
        }

        @Test
        @DisplayName("Should compute valid birth-death pairs")
        void shouldComputeValidBirthDeathPairs() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();

            // Act
            PersistenceDiagram diagram = topologicalAnalysisService.computePersistenceDiagram(screenplay);

            // Assert
            assertThat(diagram.getBirthDeathPairs())
                    .isNotEmpty()
                    .allMatch(pair -> pair.getBirth() <= pair.getDeath())
                    .allMatch(pair -> pair.getDimension() >= 0);
        }
    }

    private Screenplay createTestScreenplay() {
        Screenplay screenplay = new Screenplay();
        screenplay.setTitle("Test Screenplay");

        Scene scene = new Scene();
        scene.setContent("Test scene content");
        scene.setScreenplay(screenplay);
        screenplay.getScenes().add(scene);

        return screenplay;
    }
}