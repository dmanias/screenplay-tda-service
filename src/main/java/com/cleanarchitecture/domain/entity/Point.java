package com.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private double birth;
    private double death;
    private int dimension;

    @ManyToOne
    @JoinColumn(name = "persistence_diagram_id")
    private PersistenceDiagram persistenceDiagram;
}

