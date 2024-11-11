package com.cleanarchitecture.application.mapper;

import com.cleanarchitecture.application.dto.*;
import com.cleanarchitecture.domain.entity.*;
import com.cleanarchitecture.domain.valueobject.CreativeMetrics;
import com.cleanarchitecture.domain.valueobject.PatternMetrics;
import com.cleanarchitecture.domain.valueobject.TopologicalFeatures;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreenplayDTOMapper {
    ScreenplayDTO toDTO(Screenplay screenplay);
    Screenplay toDomain(ScreenplayDTO dto);

    SceneDTO toDTO(Scene scene);
    Scene toDomain(SceneDTO dto);

    DialogueDTO toDTO(Dialogue dialogue);
    Dialogue toDomain(DialogueDTO dto);

    ScreenplayMetricsDTO toDTO(ScreenplayMetrics metrics);
    ScreenplayMetrics toDomain(ScreenplayMetricsDTO dto);

    TopologicalFeaturesDTO toDTO(TopologicalFeatures features);
    TopologicalFeatures toDomain(TopologicalFeaturesDTO dto);

    PersistenceDiagramDTO toDTO(PersistenceDiagram diagram);
    PersistenceDiagram toDomain(PersistenceDiagramDTO dto);

    PointDTO toDTO(Point point);
    Point toDomain(PointDTO dto);

    CreativeMetricsDTO toDTO(CreativeMetrics metrics);
    CreativeMetrics toDomain(CreativeMetricsDTO dto);

    PatternMetricsDTO toDTO(PatternMetrics metrics);
    PatternMetrics toDomain(PatternMetricsDTO dto);
}