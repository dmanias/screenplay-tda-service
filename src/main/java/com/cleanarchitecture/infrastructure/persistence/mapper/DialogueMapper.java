package com.cleanarchitecture.infrastructure.persistence.mapper;

import com.cleanarchitecture.domain.entity.Dialogue;
import com.cleanarchitecture.infrastructure.persistence.entity.DialogueJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class DialogueMapper {

    public Dialogue toDomain(DialogueJpaEntity entity) {
        if (entity == null) return null;

        Dialogue domain = new Dialogue();
        domain.setId(entity.getId());
        domain.setCharacterName(entity.getCharacterName());
        domain.setContent(entity.getContent());

        return domain;
    }

    public DialogueJpaEntity toJpaEntity(Dialogue domain) {
        if (domain == null) return null;

        DialogueJpaEntity entity = new DialogueJpaEntity();
        entity.setId(domain.getId());
        entity.setCharacterName(domain.getCharacterName());
        entity.setContent(domain.getContent());

        return entity;
    }
}