package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.DetalleStock;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.Set;

public class StockCreado extends DomainEvent {
    private final Set<DetalleStock> stock;

    public StockCreado(String type, Set<DetalleStock> stock) {
        super("Stock.stockCreado");
        this.stock = stock;
    }

    public Set<DetalleStock> getStock(){return stock;}
}
