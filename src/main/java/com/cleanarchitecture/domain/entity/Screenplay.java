package com.cleanarchitecture.domain.entity;
import com.cleanarchitecture.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Screenplay {
    private String id;
    private String title;
    private String premise;
    private List<Scene> scenes = new ArrayList<>();
    private ScreenplayMetrics metrics;  // Renamed from screenplayMetrics for consistency
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}