package com.FabricaAlimentoMascotas.ControlProduccion.domain.enums;

public enum TipoDocumento {

    CEDULA ("cc"),
    PERMISOESPECIALPERMANENCIA("pep"),
    PASAPORTE("ps"),
    CEDULAEXTRANJERIA("ce");
    private final String tipoDocumento;

    TipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }
}


