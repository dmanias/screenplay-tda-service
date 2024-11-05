package com.cleanarchitecture.domain.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeMetrics {

    private double aiCreativePotential;
    private double humanFoundation;
    private double collaborationScore;
}


