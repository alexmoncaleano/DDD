package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Presentacion;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

public class DetalleStockCreado extends DomainEvent {
    private final Integer unidades;
    private final Presentacion presentacion;

    public DetalleStockCreado(Integer unidades, Presentacion presentacion) {
        super("DetalleStock.DetalleStockCreado");
        this.unidades = unidades;
        this.presentacion = presentacion;
    }

    public Presentacion getPresentacion(){return presentacion;}
    public Integer getUnidades(){return unidades;}

}
