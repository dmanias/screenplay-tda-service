package com.cleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "dialogues")
@Data
public class DialogueJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "character_name")
    private String characterName;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id")
    private SceneJpaEntity sceneJpaEntity;  // This is the correct field name
}
