package com.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screenplay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String premise;

    @OneToMany(mappedBy = "screenplay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scene> scenes =  new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "screenplay")
    private ScreenplayMetrics screenplayMetrics;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
