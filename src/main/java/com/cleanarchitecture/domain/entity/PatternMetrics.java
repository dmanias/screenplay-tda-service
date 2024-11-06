package com.cleanarchitecture.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatternMetrics {

    private double humanPatternScore;
    private double aiPatternScore;
    private double hybridScore;
}
