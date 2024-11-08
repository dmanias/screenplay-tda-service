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
public class CreativeMetricsEmbeddable {
    @Column(name = "ai_creative_potential")
    private double aiCreativePotential;

    @Column(name = "human_foundation")
    private double humanFoundation;

    @Column(name = "collaboration_score")
    private double collaborationScore;
}
