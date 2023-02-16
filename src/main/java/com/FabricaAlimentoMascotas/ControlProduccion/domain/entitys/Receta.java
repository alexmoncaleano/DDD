package com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.RecetaId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Entity;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.util.Set;

public class Receta extends Entity<RecetaId> {

    private Set<MateriaPrima> ListaIngredientes;


    public Receta(RecetaId id, Set<MateriaPrima> listaIngredientes) {
        super(id);
        ListaIngredientes = listaIngredientes;
    }

    public Set<MateriaPrima> listaIngredientes(){
        return this.ListaIngredientes;
    }
}
