package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;

public class Presentacion implements ValueObject<Presentacion.Props> {

    private Cantidad cantidad;
    private Precio precio;


    public Presentacion(Cantidad cantidad, Precio precio) {

        this.cantidad = Objects.requireNonNull(cantidad);
        this.precio = Objects.requireNonNull(precio);

    }

    public Cantidad getCantidad() {
        return cantidad;
    }

    public void setCantidad(Cantidad cantidad) {
        this.cantidad = cantidad;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Cantidad cantidad() {
                return cantidad;
            }

            @Override
            public Precio precio() {
                return precio;
            }
        };
    }


    public interface Props {

        Cantidad cantidad ();

        Precio precio();

    }
}
