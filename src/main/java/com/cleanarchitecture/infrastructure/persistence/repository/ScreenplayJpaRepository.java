package com.cleanarchitecture.infrastructure.persistence.repository;

import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenplayJpaRepository extends JpaRepository<ScreenplayJpaEntity, String> {
    List<ScreenplayJpaEntity> findByTitleContaining(String title);  // Changed to match domain interface
}

