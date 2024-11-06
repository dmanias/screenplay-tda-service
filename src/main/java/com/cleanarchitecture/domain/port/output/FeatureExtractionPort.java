package com.cleanarchitecture.domain.port.output;

import com.cleanarchitecture.domain.entity.Screenplay;

public interface FeatureExtractionPort {
    double[] extractGradientFeatures(Screenplay screenplay);
    double[] extractConnectivityPatterns(Screenplay screenplay);
    double[] extractSpatialStructures(Screenplay screenplay);
}
