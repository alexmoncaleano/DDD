package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;
import java.util.Set;

public class Informacion implements ValueObject<Set<Presentacion>> {

    private Set<Presentacion> presentaciones;

    public Informacion(Set<Presentacion> presentaciones) {
        this.presentaciones = Objects.requireNonNull(presentaciones, "La presentacion es necesaria");
    }
    public Informacion(){}
    public Set<Presentacion> getPresentaciones() {
        return presentaciones;
    }
    public void setPresentaciones(Set<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
    @Override
    public Set<Presentacion> value() {
        return presentaciones;
    }
}
