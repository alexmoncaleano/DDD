package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <R extends Command> {
    List<DomainEvent> apply(R command);

}
