package com.cleanarchitecture.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersistenceDiagram {
    private String id;
    private List<Double> bettiNumbers;
    private List<Point> birthDeathPairs;
    private double wassersteinDistance;
    private ScreenplayMetrics screenplayMetrics;

    public void setScreenplayMetrics(ScreenplayMetrics screenplayMetrics) {
        this.screenplayMetrics = screenplayMetrics;
    }
}

