package com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Entity;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

public class RequisicionProducto extends Entity<Identity> {

    private Cantidad cantidad;
    private Producto producto;

    public RequisicionProducto(Identity id, Cantidad cantidad, Producto producto) {
        super(id);
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Cantidad cantidad(){return cantidad;}
    public Producto producto(){return producto;}
}
