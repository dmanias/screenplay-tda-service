package com.cleanarchitecture.application.service;

import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.valueobject.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModalLogicServiceUnitTest {

    private ModalLogicService modalLogicService;

    private Screenplay screenplay;
    private ScreenplayMetrics screenplayMetrics;
    private CreativeMetrics creativeMetrics;

    @BeforeEach
    void setup() {
        modalLogicService = new ModalLogicService();

        //Initialize test data
        screenplay = new Screenplay();
        screenplay.setId("test-id");
        screenplay.setTitle("Test Screenplay");
        screenplay.setScenes(new ArrayList<>());
        screenplay.setCreatedAt(LocalDateTime.now());
        screenplay.setUpdatedAt(LocalDateTime.now());

        creativeMetrics = new CreativeMetrics();
        screenplayMetrics = new ScreenplayMetrics();
        screenplayMetrics.setCreativeMetrics(creativeMetrics);
        screenplay.setScreenplayMetrics(screenplayMetrics);
    }

    @Nested
    @DisplayName("validateCreativeProcess Tests")
    class validateCreativeProcessTests {

        @Test
        @DisplayName("Should return valid result when all metrics are above thresholds")
        void shouldReturnValidResultWhenAllMetricsAboveThresholds() {
            //Arrange
            creativeMetrics.setAiCreativePotential(0.6);
            creativeMetrics.setHumanFoundation(0.5);
            creativeMetrics.setCollaborationScore(0.8);
            //Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);
            //Assert
            assertTrue(result.isValid());
            assertTrue(result.potentialValid());
            assertTrue(result.foundationValid());
            assertTrue(result.collaborationValid());
        }

        @Test
        @DisplayName("Should return invalid result when all metrics are below thresholds")
        void shouldReturnInvalidResultWhenAllMetricsBelowThresholds() {
            // Arrange
            creativeMetrics.setAiCreativePotential(0.4);    // < 0.5
            creativeMetrics.setHumanFoundation(0.3);        // < 0.4
            creativeMetrics.setCollaborationScore(0.6);     // < 0.7

            // Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            // Assert
            assertFalse(result.isValid());
            assertFalse(result.potentialValid());
            assertFalse(result.foundationValid());
            assertFalse(result.collaborationValid());
        }

        @ParameterizedTest(name = "AI Potential: {0}, Human Foundation: {1}, Collaboration: {2} -> Valid: {3}")
        @CsvSource({
                "0.6, 0.5, 0.8, true",   // All valid
                "0.4, 0.5, 0.8, false",  // Invalid AI potential
                "0.6, 0.3, 0.8, false",  // Invalid human foundation
                "0.6, 0.5, 0.6, false"   // Invalid collaboration
        })
        @DisplayName("Should validate creative process with different metric combinations")
        void shouldValidateCreativeProcessWithDifferentMetrics(
                double aiPotential, double humanFoundation,
                double collaboration, boolean expectedValid) {

            // Arrange
            creativeMetrics.setAiCreativePotential(aiPotential);
            creativeMetrics.setHumanFoundation(humanFoundation);
            creativeMetrics.setCollaborationScore(collaboration);

            // Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            // Assert
            assertEquals(expectedValid, result.isValid());
        }

        @Test
        @DisplayName("Should handle edge case with maximum values")
        void shouldHandleEdgeCaseWithMaximumValues() {
            // Arrange
            creativeMetrics.setAiCreativePotential(1.0);
            creativeMetrics.setHumanFoundation(1.0);
            creativeMetrics.setCollaborationScore(1.0);

            // Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            // Assert
            assertTrue(result.isValid());
            assertThat(result)
                    .matches(ValidationResult::potentialValid)
                    .matches(ValidationResult::foundationValid)
                    .matches(ValidationResult::collaborationValid);
        }

        @Test
        @DisplayName("Should handle edge case with minimum values")
        void shouldHandleEdgeCaseWithMinimumValues() {
            //Arrange
            creativeMetrics.setAiCreativePotential(0.0);
            creativeMetrics.setHumanFoundation(0.0);
            creativeMetrics.setCollaborationScore(0.0);

            //Act
            ValidationResult result = modalLogicService.validateCreativeProcess(screenplay);

            //Assert
            assertFalse(result.isValid());
            assertThat(result)
                    .matches(r-> !r.potentialValid())
                    .matches(r -> !r.foundationValid())
                    .matches(r-> !r.collaborationValid());
        }
    }
}