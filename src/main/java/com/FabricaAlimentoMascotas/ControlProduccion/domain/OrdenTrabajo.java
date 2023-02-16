package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.RequisicionProducto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Descripcion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.RequisicionMateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.AggregateRoot;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.util.Objects;
import java.util.Set;

public class OrdenTrabajo extends AggregateRoot<Identity> {

    protected Set<RequisicionProducto> requisiciones;
    protected Descripcion descripcion;
    protected RequisicionMateriaPrima requisicionMateriaPrima;

    public OrdenTrabajo(Identity id, Set<RequisicionProducto> requisiciones, Descripcion descripcion) {
        super(id);
        this.requisiciones = Objects.requireNonNull(requisiciones);
        this.descripcion = Objects.requireNonNull(descripcion);
    }
}
