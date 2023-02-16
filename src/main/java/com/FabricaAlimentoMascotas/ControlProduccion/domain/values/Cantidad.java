package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;


import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Arrays;
import java.util.Objects;

public class Cantidad implements ValueObject<Cantidad.Props>{

    private Integer cantidad;
    private WeightUnit unidadMedida;

    public Cantidad(Integer cantidad, WeightUnit unidadMedida) {
        if(cantidad <= 0){
            throw new IllegalArgumentException(cantidad + "Valor no puede ser negativo o cero");
        }
        this.cantidad = Objects.requireNonNull(cantidad);

        if (!Arrays.asList(WeightUnit.values()).contains(unidadMedida)) {
            throw new IllegalArgumentException("Invalid unit of measure: " + unidadMedida);
        }
        this.unidadMedida = Objects.requireNonNull(unidadMedida);
    }

    public Cantidad(){}

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Integer cantidad() {
                return cantidad;
            }

            @Override
            public WeightUnit unidadMedida() {
                return unidadMedida;
            }
        };
    }

    @Override
    public String toString() {
        return "OvCantidad{" +
                "cantidad=" + cantidad +
                ", unidadMedida=" + unidadMedida.getSymbol() +
                '}';
    }

    public interface Props {
        Integer cantidad ();
        WeightUnit unidadMedida();
    }
}
