package com.cleanarchitecture.infrastructure.persistence.adapter;

import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.port.output.ScreenplayMetricsRepository;
import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayMetricsJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.mapper.ScreenplayMetricsMapper;
import com.cleanarchitecture.infrastructure.persistence.repository.ScreenplayMetricsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScreenplayMetricsRepositoryAdapter implements ScreenplayMetricsRepository {

    private final ScreenplayMetricsJpaRepository jpaRepository;
    private final ScreenplayMetricsMapper mapper;

    @Override
    @Transactional
    public ScreenplayMetrics save(ScreenplayMetrics metrics) {
        ScreenplayMetricsJpaEntity jpaEntity = mapper.toJpaEntity(metrics);
        ScreenplayMetricsJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ScreenplayMetrics> findByScreenplayId(String screenplayId) {
        return jpaRepository.findByScreenplayIdWithDetails(screenplayId)
                .map(mapper::toDomain);
    }
}
