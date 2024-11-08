package com.cleanarchitecture.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreenplayTest {
    private Screenplay screenplay;

    @BeforeEach
    void setUp() {
        screenplay = new Screenplay();
        screenplay.setId("test-id"); // Set a test ID
    }

    @Test
    void whenCreatingNewScreenplay_thenTimestampsAreSet() {
        // Act
        LocalDateTime before = LocalDateTime.now();
        screenplay.onCreate();
        LocalDateTime after = LocalDateTime.now();

        // Assert
        assertNotNull(screenplay.getCreatedAt());
        assertNotNull(screenplay.getUpdatedAt());
        assertTrue(screenplay.getCreatedAt().isEqual(screenplay.getUpdatedAt()));
        assertTrue(
                !screenplay.getCreatedAt().isBefore(before) &&
                        !screenplay.getCreatedAt().isAfter(after)
        );
    }

    @Test
    void whenUpdatingScreenplay_thenUpdateTimestampIsUpdated() {
        // Arrange
        screenplay.onCreate(); // Set initial timestamps
        LocalDateTime initialUpdateTime = screenplay.getUpdatedAt();

        // Wait to ensure different timestamp
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            fail("Test interrupted");
        }

        // Act
        screenplay.onUpdate();

        // Assert
        assertTrue(screenplay.getUpdatedAt().isAfter(initialUpdateTime));
        assertEquals(screenplay.getCreatedAt(), initialUpdateTime);
    }
}