package com.cleanarchitecture.infrastructure.persistence.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatternMetricsEmbeddable {
    @Column(name = "human_pattern_score")
    private double humanPatternScore;

    @Column(name = "ai_pattern_score")
    private double aiPatternScore;

    @Column(name = "hybrid_score")
    private double hybridScore;
}
