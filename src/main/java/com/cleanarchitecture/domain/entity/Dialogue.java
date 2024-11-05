package com.cleanarchitecture.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dialogue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String characterName;
    private String content;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene;

}
