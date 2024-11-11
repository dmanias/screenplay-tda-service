package com.cleanarchitecture.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    private String id;
    private double birth;
    private double death;
    private int dimension;

    public boolean isValid() {
        return birth <= death;
    }
}