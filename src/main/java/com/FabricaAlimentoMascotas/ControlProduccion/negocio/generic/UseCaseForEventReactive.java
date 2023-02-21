package com.FabricaAlimentoMascotas.ControlProduccion.negocio.generic;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class UseCaseForEventReactive<R extends DomainEvent> implements Function<Mono<R>, Flux<DomainEvent>> {

    public abstract Flux<DomainEvent> apply (Mono<R> rMono);

}
