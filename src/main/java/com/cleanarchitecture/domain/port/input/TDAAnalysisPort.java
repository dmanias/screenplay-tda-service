package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.entity.ScreenplayMetrics;

public interface TDAAnalysisPort {
    ScreenplayMetrics analyzeScreenplay(Screenplay screenplay);
}
