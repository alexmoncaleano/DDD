package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.RecetaId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.HashSet;
import java.util.Set;

public class RecetaCreada extends DomainEvent {

    private final RecetaId recetaId;

    public RecetaCreada(RecetaId recetaId) {
        super("Receta.recetaCreada");
        this.recetaId = recetaId;
    }
    public RecetaId getRecetaId(){return recetaId;}
}
