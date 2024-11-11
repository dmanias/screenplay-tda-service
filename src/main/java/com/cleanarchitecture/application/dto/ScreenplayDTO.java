package com.cleanarchitecture.application.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScreenplayDTO {
    private String id;
    private String title;
    private String premise;
    private List<SceneDTO> scenes;
    private ScreenplayMetricsDTO metrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
