package com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface Repositorio {

    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findById(String agregateRootId);

    Flux<DomainEvent> findByIdReactive(String agregateRootId);

    Mono<DomainEvent> saveEventReactive(DomainEvent event);
}
