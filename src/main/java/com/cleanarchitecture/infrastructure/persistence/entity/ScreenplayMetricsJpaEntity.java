package com.cleanarchitecture.infrastructure.persistence.entity;

import com.cleanarchitecture.infrastructure.persistence.embeddable.CreativeMetricsEmbeddable;
import com.cleanarchitecture.infrastructure.persistence.embeddable.PatternMetricsEmbeddable;
import com.cleanarchitecture.infrastructure.persistence.embeddable.TopologicalFeaturesEmbeddable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screenplay_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenplayMetricsJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "screenplay_id")
    private ScreenplayJpaEntity screenplay;

    @Embedded
    private TopologicalFeaturesEmbeddable topologicalFeatures;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "screenplayMetrics")
    private PersistenceDiagramJpaEntity persistenceDiagram;

    @Embedded
    private CreativeMetricsEmbeddable creativeMetrics;

    @Embedded
    private PatternMetricsEmbeddable patternMetrics;
}