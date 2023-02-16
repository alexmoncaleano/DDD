package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;

public class Descripcion implements ValueObject<String> {

    private String descripcion;

    public Descripcion(String descripcion) {

        this.descripcion = Objects.requireNonNull(validarDescripcion(descripcion), "La descripcion no puede ser nula");
    }
    private String validarDescripcion(String descripcion){
        if(descripcion.length() > 500){
            throw new IllegalArgumentException("La descripcion no puede contener mas de 500 caracteres");
        }
        return descripcion;
    }

    @Override
    public String value() {
        return descripcion;
    }
}
