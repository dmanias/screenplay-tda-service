package com.cleanarchitecture.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {

    private Scene scene;
    private Screenplay screenplay;

    @BeforeEach
    void setup() {
        screenplay = new Screenplay();
        scene = new Scene();
    }

    @Test
    @DisplayName("Should create new scene with basic properties")
    void shouldCreateNewScene() {
        //Arrange
        String content = "Scene content";
        Integer sequence = 1;

        //Act
        scene.setContent(content);
        scene.setSequence(sequence);

        //Assert
        assertEquals(content, scene.getContent());
        assertEquals(sequence, scene.getSequence());
        assertNotNull(scene.getDialogues());
        assertTrue(scene.getDialogues().isEmpty());
    }

    @Test
    @DisplayName("Should set screenplay reference")
    void shouldSetScreenplayReference() {
        //Act
        scene.setScreenplay(screenplay);

        //Assert
        assertEquals(screenplay, scene.getScreenplay());
    }

    @Test
    @DisplayName("should add dialogue to scene")
    void shouldAddDialogue() {
        //Arrange
        Dialogue dialogue = new Dialogue();
        dialogue.setCharacterName("John");
        dialogue.setContent("Hello, world!");

        //Act
        scene.getDialogues().add(dialogue);
        dialogue.setScene(scene);

        //Assert
        assertEquals(1, scene.getDialogues().size());
        assertEquals(dialogue, scene.getDialogues().get(0));
        assertEquals(scene, dialogue.getScene());
    }

}