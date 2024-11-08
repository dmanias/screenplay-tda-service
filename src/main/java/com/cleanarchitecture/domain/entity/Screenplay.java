package com.cleanarchitecture.domain.entity;
import com.cleanarchitecture.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Screenplay {
    private String id;
    private String title;
    private String premise;
    private List<Scene> scenes = new ArrayList<>();
    private ScreenplayMetrics metrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addScene(Scene scene) {
        scenes.add(scene);
        scene.setScreenplay(this);
    }

    public void validate() {
        if (title == null || title.trim().isEmpty()) {
            throw new DomainException("Title cannot be empty");
        }
    }
}