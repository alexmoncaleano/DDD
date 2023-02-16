package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.EventChange;

import java.util.List;

public interface UseCaseForEvent <R extends DomainEvent> {
    List<DomainEvent> apply(R domainEvent);
}
