package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;

public class MateriaPrima implements ValueObject <MateriaPrima.Props> {

    private String nombre;
    private Cantidad cantidad;
    private Informacion informacion;

    public MateriaPrima(String nombre, Cantidad cantidad, Informacion informacion) {

        this.nombre = Objects.requireNonNull(validacionNombre(nombre));
        this.cantidad = Objects.requireNonNull(cantidad);
        this.informacion = Objects.requireNonNull(informacion);
    }

    public MateriaPrima(String nombre, Cantidad cantidad) {

        this.nombre = Objects.requireNonNull(validacionNombre(nombre));
        this.cantidad = Objects.requireNonNull(cantidad);
    }

    private String validacionNombre(String nombre){
        if (nombre.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede tener mas de 30 caracteres");
        }
        return nombre;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String nombre() {
                return nombre;
            }

            @Override
            public Cantidad cantidad() {
                return cantidad;
            }

            @Override
            public Informacion informacion() {
                return informacion;
            }
        };
    }

    public interface Props {

        String nombre ();

        Cantidad cantidad();

        Informacion informacion();

    }
}
