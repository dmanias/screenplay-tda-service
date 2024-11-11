package com.cleanarchitecture.application.dto;

import lombok.Data;

@Data
public class SceneDTO {
    private String id;
    private Integer sequence;
    private String content;
    private List<DialogueDTO> dialogues;
}