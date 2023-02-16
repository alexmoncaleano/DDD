package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Informacion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.HashSet;

public class MateriaPrimaCreada extends DomainEvent {
    private final String nombre;
    private final Cantidad cantidad;


    public MateriaPrimaCreada(String nombre, Cantidad cantidad, Informacion informacion) {
        super("MateriaPrima.MateriaPrimaCreada");
        this.nombre = nombre;
        this.cantidad = cantidad;
        new numeroElementosCreado(new HashSet<MateriaPrima>());

    }

    public MateriaPrimaCreada(String nombre, Cantidad cantidad) {
        super("MateriaPrima.MateriaPrimaCreada");
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre(){return nombre;}
    public Cantidad getCantidad(){return cantidad;}

}
