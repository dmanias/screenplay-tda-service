package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.validation.BaseValidator;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.domain.valueobject.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("TDAAnalysisService Unit Tests")
class TDAAnalysisServiceUnitTest {

    @Mock
    private BaseValidator<Screenplay> baseValidator;

    @Mock
    private ModalLogicService modalLogicService;

    @Mock
    private TopologicalAnalysisService topologicalAnalysisService;

    @Mock
    private PatternAnalysisService patternAnalysisService;

    private TDAAnalysisService tdaAnalysisService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        tdaAnalysisService = new TDAAnalysisService(
                baseValidator,
                modalLogicService,
                topologicalAnalysisService,
                patternAnalysisService
        );
        setupMocks();
    }

    private void setupMocks() {
        // Setup mock behaviors
        doNothing().when(baseValidator).validate(any());

        when(modalLogicService.validateCreativeProcess(any()))
                .thenReturn(ValidationResult.valid());

        when(topologicalAnalysisService.extractFeatures(any()))
                .thenReturn(createTestFeatures());

        when(topologicalAnalysisService.computePersistenceDiagram(any()))
                .thenReturn(createTestDiagram());

        when(patternAnalysisService.analyzePatterns(any(), any()))
                .thenReturn(createTestPatternMetrics());
    }

    @Test
    @DisplayName("Should analyze screenplay successfully")
    void shouldAnalyzeScreenplaySuccessfully() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        ScreenplayMetrics metrics = tdaAnalysisService.analyzeScreenplay(screenplay);

        // Assert
        assertThat(metrics).isNotNull();
        verify(baseValidator).validate(screenplay);
        verify(modalLogicService).validateCreativeProcess(screenplay);
        verify(topologicalAnalysisService).extractFeatures(screenplay);
        verify(topologicalAnalysisService).computePersistenceDiagram(screenplay);
        verify(patternAnalysisService).analyzePatterns(any(), any());

        assertThat(metrics.getScreenplay()).isEqualTo(screenplay);
        assertThat(metrics.getTopologicalFeatures()).isNotNull();
        assertThat(metrics.getPersistenceDiagram()).isNotNull();
        assertThat(metrics.getPatternMetrics()).isNotNull();
    }

    @Test
    @DisplayName("Should set up bidirectional relationships")
    void shouldSetUpBidirectionalRelationships() {
        // Arrange
        Screenplay screenplay = createTestScreenplay();

        // Act
        ScreenplayMetrics metrics = tdaAnalysisService.analyzeScreenplay(screenplay);

        // Assert
        assertThat(screenplay.getScreenplayMetrics()).isEqualTo(metrics);
        assertThat(metrics.getPersistenceDiagram().getScreenplayMetrics()).isEqualTo(metrics);
    }

    private Screenplay createTestScreenplay() {
        Screenplay screenplay = new Screenplay();
        screenplay.setTitle("Test Screenplay");
        return screenplay;
    }

    private TopologicalFeatures createTestFeatures() {
        return TopologicalFeatures.builder()
                .gradientFeatures(new double[]{0.5, 0.6, 0.7})
                .connectivityPatterns(new double[]{0.4, 0.5, 0.6})
                .spatialStructures(new double[]{0.3, 0.4, 0.5})
                .coherenceScore(0.75)
                .complexityMeasure(0.65)
                .build();
    }

    private PersistenceDiagram createTestDiagram() {
        return PersistenceDiagram.builder()
                .bettiNumbers(java.util.Arrays.asList(1.0, 2.0, 1.0))
                .wassersteinDistance(0.5)
                .build();
    }

    private PatternMetrics createTestPatternMetrics() {
        return new PatternMetrics(0.8, 0.7, 0.75);
    }
}
