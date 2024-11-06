package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.entity.TopologicalFeatures;

public interface TopologicalAnalysisPort {
    TopologicalFeatures extractFeatures(Screenplay screenplay);
    PersistenceDiagram computePersistenceDiagram(Screenplay screenplay);
}
