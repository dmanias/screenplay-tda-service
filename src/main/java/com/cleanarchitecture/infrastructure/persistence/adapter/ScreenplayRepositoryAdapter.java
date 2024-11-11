package com.cleanarchitecture.infrastructure.persistence.adapter;

import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.port.output.ScreenplayRepository;
import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.mapper.ScreenplayMapper;
import com.cleanarchitecture.infrastructure.persistence.repository.ScreenplayJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScreenplayRepositoryAdapter implements ScreenplayRepository {

    private final ScreenplayJpaRepository jpaRepository;
    private final ScreenplayMapper mapper;

    @Override
    @Transactional
    public Screenplay save(Screenplay screenplay) {
        ScreenplayJpaEntity jpaEntity = mapper.toJpaEntity(screenplay);
        ScreenplayJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Screenplay> findById(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Screenplay> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Screenplay> findByTitleContaining(String title) {
        return jpaRepository.findByTitleContaining(title).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
