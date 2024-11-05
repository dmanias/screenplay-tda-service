package com.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.desktop.ScreenSleepEvent;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer sequence;
    private String content;

    @ManyToOne
    @JoinColumn(name = "screenplay_id")
    private Screenplay screenplay;

    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dialogue> dialogues = new ArrayList<>();
}
