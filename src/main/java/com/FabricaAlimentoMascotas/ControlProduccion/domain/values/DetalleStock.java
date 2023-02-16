package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;
import java.util.Set;

public class DetalleStock implements ValueObject<DetalleStock.Props> {

    private Integer unidades;
    private Presentacion presentacion;

    public DetalleStock(Integer unidades, Presentacion presentacion) {
        if(unidades <= 0){
            throw new IllegalArgumentException("Las unidades no pueden ser 0 o negativas");
        }
        this.unidades = Objects.requireNonNull(unidades, "Las unidades no pueden ser nulas");
        this.presentacion = Objects.requireNonNull(presentacion, "La presentacion es necesaria");
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Integer unidades() {
                return unidades;
            }

            @Override
            public Presentacion presentacion() {
                return presentacion;
            }
        };
    }

    public interface Props{
        Integer unidades();
        Presentacion presentacion();
    }
}
