package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Precio;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

public class PresentacionCreada extends DomainEvent {

    private final Cantidad cantidad;
    private final Precio precio;


    public PresentacionCreada(Cantidad cantidad, Precio precio) {
        super("Presentacion.PresentacionCreada");
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Cantidad getCantidad(){
        return cantidad;
    }
    public Precio getPrecio(){return precio;}
}
