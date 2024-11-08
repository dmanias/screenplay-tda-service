package com.cleanarchitecture.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DialogueTest {

    private Scene scene;
    private Dialogue dialogue;

    @BeforeEach
    void setup(){
        scene = new Scene();
    }

    @Test
    @DisplayName("Should create new Dialogue with basic properties")
    void shouldCreateNewDialogue() {
        //Arrange
        String characterName = "John Doe";
        String content = "Hello, this is a test dialogue";

        //Act
        dialogue.setCharacterName(characterName);
        dialogue.setContent(content);

        //Assert
        assertNotNull(dialogue);
        assertEquals(characterName, dialogue.getCharacterName());
        assertEquals(content, dialogue.getContent());
    }

    @Test
    @DisplayName("Should set scene reference")
    void shouldSetSceneReference() {
        //Arrange
        assertNotNull(scene);
        //Act
        dialogue.setScene(scene);
        //Assert
        assertNotNull(dialogue.getScene());
        assertEquals(scene, dialogue.getScene());
    }


}