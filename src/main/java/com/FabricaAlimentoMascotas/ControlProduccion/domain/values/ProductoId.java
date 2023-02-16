package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

public class ProductoId extends Identity {

    public ProductoId(String uuid) {
        super(uuid);
    }

    public ProductoId(){

    }

    public static ProductoId of(String uuid) {return new ProductoId(uuid);}
}
