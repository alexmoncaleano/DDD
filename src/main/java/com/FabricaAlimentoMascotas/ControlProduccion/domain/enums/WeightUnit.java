package com.FabricaAlimentoMascotas.ControlProduccion.domain.enums;

public enum WeightUnit {
    KILOGRAMS("kg"),
    GRAMS("g"),
    POUNDS("lb"),
    OUNCES("oz");

    private final String symbol;

    WeightUnit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
