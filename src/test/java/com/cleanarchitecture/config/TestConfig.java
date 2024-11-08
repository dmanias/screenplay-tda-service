package com.cleanarchitecture.config;

import com.cleanarchitecture.domain.validation.BaseValidator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public FeatureExtractionPort featureExtractionPort() {
        return new FeatureExtractionPort() {
            @Override
            public double[] extractGradientFeatures(com.cleanarchitecture.domain.entity.Screenplay screenplay) {
                return new double[]{0.5, 0.6, 0.7};
            }

            @Override
            public double[] extractConnectivityPatterns(com.cleanarchitecture.domain.entity.Screenplay screenplay) {
                return new double[]{0.4, 0.5, 0.6};
            }

            @Override
            public double[] extractSpatialStructures(com.cleanarchitecture.domain.entity.Screenplay screenplay) {
                return new double[]{0.3, 0.4, 0.5};
            }
        };
    }

    @Bean
    @Primary
    public BaseValidator screenplayValidator() {
        return new BaseValidator<>() {
            @Override
            public void validate(Object screenplay) {
                // Do nothing for tests
            }
        };
    }
}