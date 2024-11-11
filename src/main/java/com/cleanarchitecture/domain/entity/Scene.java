package com.cleanarchitecture.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scene {
    private String id;
    private Integer sequence;
    private String content;
    private Screenplay screenplay;
    private List<Dialogue> dialogues = new ArrayList<>();

    public void addDialogue(Dialogue dialogue) {
        dialogues.add(dialogue);
        dialogue.setScene(this);
    }
}