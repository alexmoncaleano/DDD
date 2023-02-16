package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

public class Detalle implements ValueObject<Detalle.Props> {

    private Producto producto;
    private Integer cantidad;
    private Presentacion presentacion;
    private Precio total;

    public Detalle(Producto producto, Integer cantidad, Presentacion presentacion) {
        this.producto = Objects.requireNonNull(producto, "El producto no puede ser nulo");
        this.cantidad = Objects.requireNonNull(validarCantidad(cantidad), "La cantidad no puede ser nula");
        this.presentacion = Objects.requireNonNull(presentacion, "La presentacion no puede ser nula");
        this.total = calcularTotal();
    }

    Integer validarCantidad(Integer cantidad){
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa o cero");
        }
        return cantidad;
    }
    private Precio calcularTotal(){
        return new Precio((presentacion.value().precio().value().precio().multiply(BigDecimal.valueOf(cantidad))), presentacion.value().precio().value().moneda());
    }


    @Override
    public Props value() {
        return new Props() {
            @Override
            public Producto producto() {
                return producto;
            }

            @Override
            public Integer cantidad() {
                return cantidad;
            }

            @Override
            public Presentacion presentacion() {
                return presentacion;
            }

            @Override
            public Precio total() {
                return total;
            }
        };
    }

    public interface Props{
        Producto producto();
        Integer cantidad();
        Presentacion presentacion();
        Precio total();
    }
}
