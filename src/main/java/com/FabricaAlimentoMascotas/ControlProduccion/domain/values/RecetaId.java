package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

public class RecetaId extends Identity {
    private RecetaId(String uuid) {
        super(uuid);
    }

    public RecetaId() {
    }

    public static RecetaId of(String uuid){
        return new RecetaId(uuid);
    }
}
