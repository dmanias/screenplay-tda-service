package com.cleanarchitecture.domain.entity;


import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
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

    public ScreenplayMetrics(Screenplay screenplay, TopologicalFeatures features, PersistenceDiagram persistenceDiagram, CreativeMetrics creativeMetrics, PatternMetrics patternMetrics) {
    }
}
