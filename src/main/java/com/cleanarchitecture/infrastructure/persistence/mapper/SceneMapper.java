package com.cleanarchitecture.infrastructure.persistence.mapper;

import com.cleanarchitecture.domain.entity.Scene;
import com.cleanarchitecture.domain.entity.Dialogue;
import com.cleanarchitecture.infrastructure.persistence.entity.SceneJpaEntity;
import com.cleanarchitecture.infrastructure.persistence.entity.DialogueJpaEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SceneMapper {

    private final DialogueMapper dialogueMapper;

    public Scene toDomain(SceneJpaEntity entity) {
        if (entity == null) return null;

        Scene domain = new Scene();
        domain.setId(entity.getId());
        domain.setSequence(entity.getSequence());
        domain.setContent(entity.getContent());

        // Handle dialogues if present
        if (entity.getDialogues() != null) {
            List<Dialogue> dialogues = entity.getDialogues().stream()
                    .map(dialogueMapper::toDomain)
                    .collect(Collectors.toList());
            domain.setDialogues(dialogues);
            // We set the bidirectional relationship in the domain model
            dialogues.forEach(dialogue -> dialogue.setScene(domain));
        }

        return domain;
    }

    public SceneJpaEntity toJpaEntity(Scene domain) {
        if (domain == null) return null;

        SceneJpaEntity entity = new SceneJpaEntity();
        entity.setId(domain.getId());
        entity.setSequence(domain.getSequence());
        entity.setContent(domain.getContent());

        // Handle dialogues if present
        if (domain.getDialogues() != null) {
            List<DialogueJpaEntity> dialogues = domain.getDialogues().stream()
                    .map(dialogueMapper::toJpaEntity)
                    .collect(Collectors.toList());
            entity.setDialogues(dialogues);
            // We set the bidirectional relationship in the JPA entity
            dialogues.forEach(dialogue -> dialogue.setSceneJpaEntity(entity));
        }

        return entity;
    }
}
