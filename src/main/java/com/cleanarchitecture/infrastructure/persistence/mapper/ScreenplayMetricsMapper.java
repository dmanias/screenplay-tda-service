package com.cleanarchitecture.infrastructure.persistence.mapper;

import com.cleanarchitecture.domain.entity.ScreenplayMetrics;
import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import com.cleanarchitecture.infrastructure.persistence.entity.ScreenplayMetricsJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.embeddable.CreativeMetricsEmbeddable;
import com.cleanarchitecture.infrastructure.persistence.embeddable.PatternMetricsEmbeddable;
import com.cleanarchitecture.infrastructure.persistence.embeddable.TopologicalFeaturesEmbeddable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScreenplayMetricsMapper {

    private final PersistenceDiagramMapper persistenceDiagramMapper;
    private final ObjectMapper objectMapper;

    public ScreenplayMetrics toDomain(ScreenplayMetricsJpaEntity entity) {
        if (entity == null) return null;

        ScreenplayMetrics domain = new ScreenplayMetrics();
        domain.setId(entity.getId());

        // Map value objects
        if (entity.getTopologicalFeatures() != null) {
            domain.setTopologicalFeatures(mapTopologicalFeaturesToDomain(entity.getTopologicalFeatures()));
        }

        if (entity.getCreativeMetrics() != null) {
            domain.setCreativeMetrics(mapCreativeMetricsToDomain(entity.getCreativeMetrics()));
        }

        if (entity.getPatternMetrics() != null) {
            domain.setPatternMetrics(mapPatternMetricsToDomain(entity.getPatternMetrics()));
        }

        // Map persistence diagram and handle bidirectional relationship
        if (entity.getPersistenceDiagram() != null) {
            var persistenceDiagram = persistenceDiagramMapper.toDomain(entity.getPersistenceDiagram());
            domain.setPersistenceDiagram(persistenceDiagram);
            persistenceDiagram.setScreenplayMetrics(domain);
        }

        return domain;
    }

    public ScreenplayMetricsJpaEntity toJpaEntity(ScreenplayMetrics domain) {
        if (domain == null) return null;

        ScreenplayMetricsJpaEntity entity = new ScreenplayMetricsJpaEntity();
        entity.setId(domain.getId());

        // Map value objects with null checks
        if (domain.getTopologicalFeatures() != null) {
            entity.setTopologicalFeatures(mapTopologicalFeaturesToJpa(domain.getTopologicalFeatures()));
        }

        if (domain.getCreativeMetrics() != null) {
            entity.setCreativeMetrics(mapCreativeMetricsToJpa(domain.getCreativeMetrics()));
        }

        if (domain.getPatternMetrics() != null) {
            entity.setPatternMetrics(mapPatternMetricsToJpa(domain.getPatternMetrics()));
        }

        // Map persistence diagram and handle bidirectional relationship
        if (domain.getPersistenceDiagram() != null) {
            var persistenceDiagram = persistenceDiagramMapper.toJpaEntity(domain.getPersistenceDiagram());
            entity.setPersistenceDiagram(persistenceDiagram);
            persistenceDiagram.setScreenplayMetrics(entity);
        }

        return entity;
    }

    private TopologicalFeatures mapTopologicalFeaturesToDomain(TopologicalFeaturesEmbeddable embeddable) {
        try {
            if (embeddable == null) return null;

            double[] gradientFeatures = objectMapper.readValue(
                    embeddable.getGradientFeaturesJson(), double[].class);
            double[] connectivityPatterns = objectMapper.readValue(
                    embeddable.getConnectivityPatternsJson(), double[].class);
            double[] spatialStructures = objectMapper.readValue(
                    embeddable.getSpatialStructuresJson(), double[].class);

            return new TopologicalFeatures(
                    gradientFeatures,
                    connectivityPatterns,
                    spatialStructures,
                    embeddable.getCoherenceScore(),
                    embeddable.getComplexityMeasure(),
                    embeddable.getAdditionalFeatures()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing topological features", e);
        }
    }

    private TopologicalFeaturesEmbeddable mapTopologicalFeaturesToJpa(TopologicalFeatures features) {
        try {
            if (features == null) return null;

            TopologicalFeaturesEmbeddable embeddable = new TopologicalFeaturesEmbeddable();
            embeddable.setGradientFeaturesJson(
                    objectMapper.writeValueAsString(features.gradientFeatures()));
            embeddable.setConnectivityPatternsJson(
                    objectMapper.writeValueAsString(features.connectivityPatterns()));
            embeddable.setSpatialStructuresJson(
                    objectMapper.writeValueAsString(features.spatialStructures()));
            embeddable.setCoherenceScore(features.coherenceScore());
            embeddable.setComplexityMeasure(features.complexityMeasure());
            embeddable.setAdditionalFeatures(features.additionalFeatures());

            return embeddable;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing topological features", e);
        }
    }

    private CreativeMetrics mapCreativeMetricsToDomain(CreativeMetricsEmbeddable embeddable) {
        return embeddable == null ? null : new CreativeMetrics(
                embeddable.getAiCreativePotential(),
                embeddable.getHumanFoundation(),
                embeddable.getCollaborationScore()
        );
    }

    private CreativeMetricsEmbeddable mapCreativeMetricsToJpa(CreativeMetrics metrics) {
        return metrics == null ? null : new CreativeMetricsEmbeddable(
                metrics.aiCreativePotential(),
                metrics.humanFoundation(),
                metrics.collaborationScore()
        );
    }

    private PatternMetrics mapPatternMetricsToDomain(PatternMetricsEmbeddable embeddable) {
        return embeddable == null ? null : new PatternMetrics(
                embeddable.getHumanPatternScore(),
                embeddable.getAiPatternScore(),
                embeddable.getHybridScore()
        );
    }

    private PatternMetricsEmbeddable mapPatternMetricsToJpa(PatternMetrics metrics) {
        return metrics == null ? null : new PatternMetricsEmbeddable(
                metrics.humanPatternScore(),
                metrics.aiPatternScore(),
                metrics.hybridScore()
        );
    }
}