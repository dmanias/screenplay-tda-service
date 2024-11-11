package com.cleanarchitecture.domain.entity;


import com.cleanarchitecture.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dialogue {
    private String id;
    private String characterName;
    private String content;
    private Scene scene;

    public void validate() {
        if (characterName == null || characterName.trim().isEmpty()) {
            throw new DomainException("Character name cannot be empty");
        }
    }
}
