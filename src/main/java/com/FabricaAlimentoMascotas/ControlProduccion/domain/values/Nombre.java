package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<Nombre.Props> {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    public Nombre(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {

        this.primerNombre = Objects.requireNonNull(validarDato(primerNombre), "El nombre no puede ser nulo");

        this.segundoNombre = validarDato(segundoNombre);

        this.primerApellido = Objects.requireNonNull(validarDato(primerApellido), "El apellido no puede ser nulo");

        this.segundoApellido = segundoApellido;
    }

    private String validarDato(String dato){
        if (dato.length() > 10) {
            throw new IllegalArgumentException("No puede tener más de 10 caracteres");
        }
        if (!dato.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Solo puede contener letras");
        }
        return dato;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String primerNombre() {
                return primerNombre;
            }

            @Override
            public String segundoNombre() {
                return segundoNombre;
            }

            @Override
            public String primerApellido() {
                return primerApellido;
            }

            @Override
            public String segundoApellido() {
                return segundoApellido;
            }
        };
    }

    public interface Props{
        String primerNombre();
        String segundoNombre();
        String primerApellido();
        String segundoApellido();
    }
}
