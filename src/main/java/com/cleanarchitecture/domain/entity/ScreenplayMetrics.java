package com.cleanarchitecture.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenplayMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "screenplay_id")
    private Screenplay screenplay;

    @Embedded
    private TopologicalFeatures topologicalFeatures;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "screenplayMetrics")
    private PersistenceDiagram persistenceDiagram;

    @Embedded
    private CreativeMetrics creativeMetrics;

    @Embedded
    private PatternMetrics patternMetrics;
}
