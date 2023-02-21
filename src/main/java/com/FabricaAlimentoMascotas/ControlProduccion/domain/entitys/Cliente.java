package com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ClienteId;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Contacto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Identificacion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Nombre;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Entity;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.util.Objects;

public class Cliente extends Entity<ClienteId> {

    private Nombre nombre;
    private Contacto contacto;
    private Identificacion identificacion;

    public Cliente(ClienteId id, Nombre nombre, Contacto contacto, Identificacion identificacion) {
        super(id);
        this.nombre = Objects.requireNonNull(nombre);
        this.contacto = Objects.requireNonNull(contacto);
        this.identificacion = Objects.requireNonNull(identificacion);
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public Identificacion getIdentificacion() {
        return identificacion;
    }
}

