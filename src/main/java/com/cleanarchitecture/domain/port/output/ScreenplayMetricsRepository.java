package com.cleanarchitecture.domain.port.output;

import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import java.util.Optional;

public interface ScreenplayMetricsRepository {
    ScreenplayMetrics save(ScreenplayMetrics metrics);
    Optional<ScreenplayMetrics> findByScreenplayId(String screenplayId);
}
