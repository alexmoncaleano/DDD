package com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.List;

public interface Repositorio {

    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findById(String agregateRootId);
}
