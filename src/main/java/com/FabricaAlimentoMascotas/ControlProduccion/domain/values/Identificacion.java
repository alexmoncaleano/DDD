package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.TipoDocumento;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Arrays;
import java.util.Objects;

public class Identificacion implements ValueObject<Identificacion.Props> {

    private String numeroDocumento;
    private TipoDocumento tipoDocumento;

    public Identificacion(String numeroDocumento, TipoDocumento tipoDocumento) {
        this.numeroDocumento = Objects.requireNonNull(validarNumeroDocumento(numeroDocumento), "El numero de documento no puede ser null");
        this.tipoDocumento = Objects.requireNonNull(validarTipoDocumento(tipoDocumento), "El tipo de documento no puede ser null");
    }

    String validarNumeroDocumento(String numeroDocumento){
        if (!numeroDocumento.matches("[0-9]+")) {
            throw new IllegalArgumentException("El número de documento solo debe contener números");
        }
        return numeroDocumento;
    }

    TipoDocumento validarTipoDocumento(TipoDocumento tipoDocumento){
        if (!Arrays.asList(TipoDocumento.values()).contains(tipoDocumento)) {
            throw new IllegalArgumentException("El tipo de documento no coincide con ninguno de los permitidos");
        }
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = validarNumeroDocumento(numeroDocumento);
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = validarTipoDocumento(tipoDocumento);
    }

    @Override
    public Identificacion.Props value() {
        return new Props() {
            @Override
            public String numeroDocumento() {
                return numeroDocumento;
            }

            @Override
            public TipoDocumento tipoDocumento() {
                return tipoDocumento;
            }
        };
    }

    public interface Props{
        String numeroDocumento();

        TipoDocumento tipoDocumento();
    }
}
