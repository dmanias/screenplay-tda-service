package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;

public interface PatternAnalysisPort {
    PatternMetrics analyzePatterns(TopologicalFeatures topologicalFeatures, PersistenceDiagram persistenceDiagram);
}
