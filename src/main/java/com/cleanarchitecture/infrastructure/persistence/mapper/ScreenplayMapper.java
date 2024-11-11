package com.cleanarchitecture.infrastructure.persistence.mapper;

import com.cleanarchitecture.domain.entity.Scene;
import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.infrastructure.persistence.entity.SceneJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScreenplayMapper {

    private final SceneMapper sceneMapper;
    private final ScreenplayMetricsMapper metricsMapper;

    public Screenplay toDomain(ScreenplayJpaEntity entity) {
        if (entity == null) return null;

        Screenplay domain = new Screenplay();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setPremise(entity.getPremise());

        // Handle bidirectional relationship with scenes
        List<Scene> scenes = mapScenesToDomain(entity.getScenes());
        domain.setScenes(scenes);
        scenes.forEach(scene -> scene.setScreenplay(domain));

        // Handle metrics if present
        if (entity.getMetrics() != null) {
            var metrics = metricsMapper.toDomain(entity.getMetrics());
            domain.setMetrics(metrics);  // Assuming renamed in domain entity too
            metrics.setScreenplay(domain);
        }

        return domain;
    }

    public ScreenplayJpaEntity toJpaEntity(Screenplay domain) {
        if (domain == null) return null;

        ScreenplayJpaEntity entity = new ScreenplayJpaEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setPremise(domain.getPremise());

        // Handle bidirectional relationship with scenes
        List<SceneJpaEntity> scenes = mapScenesToJpa(domain.getScenes());
        entity.setScenes(scenes);
        scenes.forEach(scene -> scene.setScreenplay(entity));

        // Handle metrics if present
        if (domain.getMetrics() != null) {
            var metricsEntity = metricsMapper.toJpaEntity(domain.getMetrics());
            entity.setMetrics(metricsEntity);
            metricsEntity.setScreenplay(entity);
        }

        return entity;
    }

    private List<Scene> mapScenesToDomain(List<SceneJpaEntity> jpaScenes) {
        if (jpaScenes == null) return List.of();

        return jpaScenes.stream()
                .map(sceneMapper::toDomain)
                .collect(Collectors.toList());
    }

    private List<SceneJpaEntity> mapScenesToJpa(List<Scene> domainScenes) {
        if (domainScenes == null) return List.of();

        return domainScenes.stream()
                .map(sceneMapper::toJpaEntity)
                .collect(Collectors.toList());
    }
}