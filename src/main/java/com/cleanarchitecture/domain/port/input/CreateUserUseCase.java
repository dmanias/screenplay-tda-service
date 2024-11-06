package com.cleanarchitecture.domain.port.input;

import com.cleanarchitecture.application.dto.UserDto;
import com.cleanarchitecture.application.dto.CreateUserCommand;

@UseCase
public interface CreateUserUseCase {
    UserDto createUser(CreateUserCommand command);
}

