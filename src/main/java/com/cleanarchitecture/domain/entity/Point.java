package com.cleanarchitecture.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private String id;
    private double birth;
    private double death;
    private int dimension;

    public boolean isValid() {
        return birth <= death;
    }
}