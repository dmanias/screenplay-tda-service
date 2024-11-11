package com.cleanarchitecture.infrastructure.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.cleanarchitecture.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.cleanarchitecture.infrastructure.persistence.repository")
public class PersistenceConfig {
}