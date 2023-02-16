package com.FabricaAlimentoMascotas.ControlProduccion.generic;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public abstract class Command implements Serializable {
    public final Instant when = Instant.now();
    private final String uuid = UUID.randomUUID().toString();

    public Command() {
    }

    public Instant when() {
        return this.when;
    }

    public String uuid() {
        return this.uuid;
    }
}