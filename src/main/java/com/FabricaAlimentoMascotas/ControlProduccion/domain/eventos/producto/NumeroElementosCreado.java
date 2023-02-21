package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.Objects;
import java.util.Set;

public class NumeroElementosCreado extends DomainEvent {

    private Integer cantidadIngredientes;

    public NumeroElementosCreado(Set<MateriaPrima> receta) {
        super("numeroElementosCreado");
        this.cantidadIngredientes = Objects.requireNonNull(contarElementos(receta),"la Receta no puede ser null");
    }

    public Integer contarElementos(Set<MateriaPrima> receta){
        return receta.size();
    }

    public Integer getCantidadIngredientes() {
        return cantidadIngredientes;
    }


}
