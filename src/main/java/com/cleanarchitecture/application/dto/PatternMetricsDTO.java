package com.cleanarchitecture.application.dto;

import lombok.Data;

@Data
public class PatternMetricsDTO {
    private double humanPatternScore;
    private double aiPatternScore;
    private double hybridScore;
}
