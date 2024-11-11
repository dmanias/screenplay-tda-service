package com.cleanarchitecture.infrastructure.persistence.mapper;

import com.cleanarchitecture.domain.entity.Point;
import com.cleanarchitecture.domain.entity.PersistenceDiagram;
import com.cleanarchitecture.infrastructure.persistence.entity.PointJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.entity.PersistenceDiagramJpaEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersistenceDiagramMapper {

    public PersistenceDiagram toDomain(PersistenceDiagramJpaEntity entity) {
        if (entity == null) return null;

        PersistenceDiagram domain = new PersistenceDiagram();
        domain.setId(entity.getId());
        domain.setBettiNumbers(entity.getBettiNumbers());
        domain.setWassersteinDistance(entity.getWassersteinDistance());
        domain.setBirthDeathPairs(mapPointsToDomain(entity.getBirthDeathPairs()));

        return domain;
    }

    public PersistenceDiagramJpaEntity toJpaEntity(PersistenceDiagram domain) {
        if (domain == null) return null;

        PersistenceDiagramJpaEntity entity = new PersistenceDiagramJpaEntity();
        entity.setId(domain.getId());
        entity.setBettiNumbers(domain.getBettiNumbers());
        entity.setWassersteinDistance(domain.getWassersteinDistance());
        entity.setBirthDeathPairs(mapPointsToJpa(domain.getBirthDeathPairs()));

        return entity;
    }

    private List<Point> mapPointsToDomain(List<PointJpaEntity> jpaPoints) {
        if (jpaPoints == null) return List.of();

        return jpaPoints.stream()
                .map(this::pointToDomain)
                .collect(Collectors.toList());
    }

    private List<PointJpaEntity> mapPointsToJpa(List<Point> domainPoints) {
        if (domainPoints == null) return List.of();

        return domainPoints.stream()
                .map(this::pointToJpa)
                .collect(Collectors.toList());
    }

    private Point pointToDomain(PointJpaEntity entity) {
        Point point = new Point();
        point.setId(entity.getId());
        point.setBirth(entity.getBirth());
        point.setDeath(entity.getDeath());
        point.setDimension(entity.getDimension());
        return point;
    }

    private PointJpaEntity pointToJpa(Point domain) {
        PointJpaEntity entity = new PointJpaEntity();
        entity.setId(domain.getId());
        entity.setBirth(domain.getBirth());
        entity.setDeath(domain.getDeath());
        entity.setDimension(domain.getDimension());
        return entity;
    }
}