package com.cleanarchitecture.infrastructure.persistence.repository;

import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayMetricsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScreenplayMetricsJpaRepository extends JpaRepository<ScreenplayMetricsJpaEntity, String> {

    @Query("SELECT m FROM ScreenplayMetricsJpaEntity m " +
            "LEFT JOIN FETCH m.persistenceDiagram " +
            "WHERE m.screenplay.id = :screenplayId")
    Optional<ScreenplayMetricsJpaEntity> findByScreenplayIdWithDetails(String screenplayId);
}
