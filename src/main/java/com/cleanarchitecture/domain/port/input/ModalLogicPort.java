package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.domain.entity.Screenplay;
import com.cleanarchitecture.domain.valueobject.ValidationResult;

public interface ModalLogicPort {
    ValidationResult validateCreativeProcess(Screenplay screenplay);
}
