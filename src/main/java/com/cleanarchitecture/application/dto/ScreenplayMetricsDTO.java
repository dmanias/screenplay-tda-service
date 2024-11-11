package com.cleanarchitecture.application.dto;

import lombok.Data;

@Data
public class ScreenplayMetricsDTO {
    private String id;
    private TopologicalFeaturesDTO topologicalFeatures;
    private PersistenceDiagramDTO persistenceDiagram;
    private CreativeMetricsDTO creativeMetrics;
    private PatternMetricsDTO patternMetrics;
}
