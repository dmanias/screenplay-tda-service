package com.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersistenceDiagram {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "metrics_id")
    private ScreenplayMetrics screenplayMetrics;

    @ElementCollection
    private List<Double> bettiNumbers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persistence_diagram_id")
    private List<Point> birthDeathPairs;

    private double wassersteinDistance;
}
