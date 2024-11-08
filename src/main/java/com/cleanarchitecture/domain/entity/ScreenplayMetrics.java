package com.cleanarchitecture.domain.entity;


import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenplayMetrics {
    private String id;
    private Screenplay screenplay;
    private TopologicalFeatures topologicalFeatures;
    private PersistenceDiagram persistenceDiagram;
    private CreativeMetrics creativeMetrics;
    private PatternMetrics patternMetrics;

    public boolean isValidAnalysis() {
        return topologicalFeatures != null &&
                creativeMetrics != null &&
                patternMetrics != null;
    }
}
