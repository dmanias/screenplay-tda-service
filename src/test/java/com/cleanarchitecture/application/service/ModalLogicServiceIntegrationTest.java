package com.cleanarchitecture.application.service;

import com.cleanarchitecture.config.TestConfig;
import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.valueobject.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for ModalLogicService
 */
@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
@DisplayName("ModalLogicService Integration Tests")
class ModalLogicServiceIntegrationTest {

    @Autowired
    private ModalLogicService modalLogicService;

    @Nested
    @DisplayName("Integration Test Scenarios")
    class IntegrationScenarios {

        @Test
        @Transactional
        @DisplayName("Should validate creative process in real Spring context")
        void shouldValidateCreativeProcessInSpringContext() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();
            setupValidMetrics(screenplay);

            // Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            // Assert
            assertThat(result.isValid()).isTrue();
            assertThat(result.potentialValid()).isTrue();
            assertThat(result.foundationValid()).isTrue();
            assertThat(result.collaborationValid()).isTrue();
        }

        @Test
        @Transactional
        @DisplayName("Should handle invalid metrics in real Spring context")
        void shouldHandleInvalidMetricsInSpringContext() {
            // Arrange
            Screenplay screenplay = createTestScreenplay();
            setupInvalidMetrics(screenplay);

            // Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            // Assert
            assertThat(result.isValid()).isFalse();
            assertThat(result.potentialValid()).isFalse();
            assertThat(result.foundationValid()).isFalse();
            assertThat(result.collaborationValid()).isFalse();
        }
    }

    private Screenplay createTestScreenplay() {
        Screenplay screenplay = new Screenplay();
        screenplay.setTitle("Test Screenplay");
        screenplay.setScenes(new ArrayList<>());
        screenplay.setCreatedAt(LocalDateTime.now());
        screenplay.setUpdatedAt(LocalDateTime.now());

        ScreenplayMetrics metrics = new ScreenplayMetrics();
        CreativeMetrics creativeMetrics = new CreativeMetrics();
        metrics.setCreativeMetrics(creativeMetrics);
        metrics.setScreenplay(screenplay);
        screenplay.setScreenplayMetrics(metrics);

        return screenplay;
    }

    private void setupValidMetrics(Screenplay screenplay) {
        CreativeMetrics metrics = screenplay.getScreenplayMetrics().getCreativeMetrics();
        metrics.setAiCreativePotential(0.8);    // > 0.5
        metrics.setHumanFoundation(0.6);        // > 0.4
        metrics.setCollaborationScore(0.9);     // > 0.7
    }

    private void setupInvalidMetrics(Screenplay screenplay) {
        CreativeMetrics metrics = screenplay.getScreenplayMetrics().getCreativeMetrics();
        metrics.setAiCreativePotential(0.4);    // < 0.5
        metrics.setHumanFoundation(0.3);        // < 0.4
        metrics.setCollaborationScore(0.6);     // < 0.7
    }
}
