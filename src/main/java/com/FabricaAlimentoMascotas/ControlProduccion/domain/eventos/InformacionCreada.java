package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Presentacion;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.Set;

public class InformacionCreada extends DomainEvent {

    private final Set<Presentacion> presentaciones;

    public InformacionCreada(String type, Set<Presentacion> presentaciones) {
        super("Informacion.informacionCreada");
        this.presentaciones = presentaciones;
    }

    public Set<Presentacion> getPresentaciones(){return presentaciones;}
}
