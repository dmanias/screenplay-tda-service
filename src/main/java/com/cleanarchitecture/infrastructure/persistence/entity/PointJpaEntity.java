package com.cleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "points")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private double birth;
    private double death;
    private int dimension;

    @ManyToOne
    @JoinColumn(name = "persistence_diagram_id")
    private PersistenceDiagramJpaEntity persistenceDiagram;
}
