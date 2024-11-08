package com.cleanarchitecture.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersistenceDiagram {
    private String id;
    private List<Double> bettiNumbers;
    private List<Point> birthDeathPairs;
    private double wassersteinDistance;

    public boolean isValid() {
        return bettiNumbers != null && !bettiNumbers.isEmpty();
    }
}
