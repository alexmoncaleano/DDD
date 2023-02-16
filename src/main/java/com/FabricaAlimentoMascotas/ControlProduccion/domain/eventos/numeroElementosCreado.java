package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.Objects;
import java.util.Set;

public class numeroElementosCreado extends DomainEvent {

    private Integer cantidadIngredientes;

    public numeroElementosCreado(Set<MateriaPrima> receta) {
        super("numeroElementosCreado");
        this.cantidadIngredientes = Objects.requireNonNull(contarElementos(receta),"la Receta no puede ser null");
    }

    public Integer contarElementos(Set<MateriaPrima> receta){
        return receta.size();
    }

    public Integer getCantidadIngredientes() {
        return cantidadIngredientes;
    }

    public void setCantidadIngredientes(Integer cantidadIngredientes) {
        this.cantidadIngredientes = cantidadIngredientes;
    }
}
