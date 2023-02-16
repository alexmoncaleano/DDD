package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;

public class Contacto implements ValueObject<Contacto.Props> {

    private String celular;
    private String email;
    private String direccion;

    public Contacto(String celular, String email, String direccion) {

        this.celular = Objects.requireNonNull(validarCeluar(celular), "El celular no puede ser nulo");


        this.email = Objects.requireNonNull(validarEmail(email), "El email no puede ser nulo");


        this.direccion = Objects.requireNonNull(direccion, "La direccion no puede ser nulo");
    }
    public String validarCeluar(String celular){
        if (!celular.matches("\\d{10}")) {
            throw new IllegalArgumentException("El número de celular solo puede contener dígitos numéricos");
        }return celular;
    }

    public String validarEmail(String email){
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(emailPattern)) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido");
        }return email;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String celular() {
                return celular;
            }
            @Override
            public String email() {
                return email;
            }
            @Override
            public String direccion() {
                return direccion;
            }
        };
    }

    public interface Props{

        String celular();
        String email();
        String direccion();

    }
}
