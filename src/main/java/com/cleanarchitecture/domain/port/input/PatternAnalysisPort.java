package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.domain.entity.PatternMetrics;
import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.entity.TopologicalFeatures;

public interface PatternAnalysisPort {
    PatternMetrics analyzePatterns(TopologicalFeatures topologicalFeatures, PersistenceDiagram persistenceDiagram);
}
