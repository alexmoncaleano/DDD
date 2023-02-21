package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.OrdenEntrega;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

public class OrdenEntregaId extends Identity {

    public OrdenEntregaId(String uuid) {
        super(uuid);
    }

    public OrdenEntregaId(){

    }

    public static OrdenEntregaId of(String uuid) {return new OrdenEntregaId(uuid);}
}


