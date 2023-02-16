package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.math.BigDecimal;
import java.util.Arrays;

public class Precio implements ValueObject<Precio.Props> {

    private BigDecimal precio;
    private Moneda moneda;

    public Precio(BigDecimal precio, Moneda moneda) {
        if(precio.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser menor o igual a cero");
        }
        this.precio = precio;
        if (!Arrays.asList(Moneda.values()).contains(moneda)) {
            throw new IllegalArgumentException("tipo de moneda no valido: " + moneda);
        }
        this.moneda = moneda;
    }

    public Precio precio(){
        return new Precio (this.precio,this.moneda);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public BigDecimal precio() {
                return precio;
            }

            @Override
            public Moneda moneda() {
                return moneda;
            }
        };
    }

    public interface Props {

        BigDecimal precio ();

        Moneda moneda();

    }
}
