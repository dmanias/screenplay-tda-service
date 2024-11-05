package com.cleanarchitecture.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.cleanarch.infrastructure.persistence.entity")
@EnableJpaRepositories("com.cleanarch.infrastructure.persistence.repository")
public class JpaConfig {
}
