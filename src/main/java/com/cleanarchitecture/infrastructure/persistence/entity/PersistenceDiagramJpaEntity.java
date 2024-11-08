package com.cleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persistence_diagrams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistenceDiagramJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "metrics_id")
    private ScreenplayMetricsJpaEntity screenplayMetrics;

    @ElementCollection
    @CollectionTable(
            name = "persistence_diagram_betti_numbers",
            joinColumns = @JoinColumn(name = "persistence_diagram_id")
    )
    private List<Double> bettiNumbers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persistence_diagram_id")
    private List<PointJpaEntity> birthDeathPairs = new ArrayList<>();

    private double wassersteinDistance;
}