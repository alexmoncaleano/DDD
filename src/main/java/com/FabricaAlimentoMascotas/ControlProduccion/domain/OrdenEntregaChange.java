package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.DetalleCompraCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Detalle;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.EventChange;

public class OrdenEntregaChange extends EventChange {

    public OrdenEntregaChange(OrdenEntrega ordenEntrega){

        apply((OrdenEntregaCreada event) -> {
           ordenEntrega.cliente = event.getCliente();
           ordenEntrega.compra = event.getCompra();
           ordenEntrega.fechaCreacion = event.getFechaCreacion();
           ordenEntrega.valorTotal = event.getValorTotal();
           ordenEntrega.estado = event.getEstado();
        });

        apply((DetalleCompraCreado event) ->{
            ordenEntrega.compra.value().detallesCompra().add(new Detalle(event.getProducto(), event.getCantidad(), event.getPresentacion()));
        });

    }

}
