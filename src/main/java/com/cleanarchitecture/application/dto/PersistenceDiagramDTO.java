package com.cleanarchitecture.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersistenceDiagramDTO {
    private String id;
    private List<Double> bettiNumbers;
    private List<PointDTO> birthDeathPairs;
    private double wassersteinDistance;
}
