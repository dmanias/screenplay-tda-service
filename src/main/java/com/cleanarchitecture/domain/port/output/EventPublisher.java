package com.cleanarchitecture.domain.port.output;

import com.cleanarchitecture.domain.event.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}
