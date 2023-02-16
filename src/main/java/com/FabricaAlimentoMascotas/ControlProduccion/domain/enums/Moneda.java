package com.FabricaAlimentoMascotas.ControlProduccion.domain.enums;

public enum Moneda {

    PESO ("$");

    private final String symbol;

    Moneda(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
