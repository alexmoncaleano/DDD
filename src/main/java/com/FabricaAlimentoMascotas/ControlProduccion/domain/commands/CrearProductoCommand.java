package com.FabricaAlimentoMascotas.ControlProduccion.domain.commands;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;

public class CrearProductoCommand extends Command {

    private String id;
    private String descripcion;
    private String recetaId;


    public CrearProductoCommand(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(String recetaId) {
        this.recetaId = recetaId;
    }
}
