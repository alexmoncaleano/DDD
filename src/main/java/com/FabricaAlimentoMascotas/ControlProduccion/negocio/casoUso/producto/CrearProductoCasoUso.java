package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto.CrearProductoCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.UseCaseForCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CrearProductoCasoUso implements UseCaseForCommand {

    private Repositorio repositorio;

    public CrearProductoCasoUso(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DomainEvent> apply(Command command) {
        CrearProductoCommand crearProducto = (CrearProductoCommand) command;
        Producto producto = new Producto(ProductoId.of(crearProducto.getId()),
                new Descripcion(crearProducto.getId()),
                new Receta(RecetaId.of(crearProducto.getRecetaId()),new HashSet<MateriaPrima>()),
                new Stock(new HashSet<DetalleStock>()),
                new Informacion(new HashSet<Presentacion>()));
        return producto.getUncommittedChanges().stream().map(event -> {
            return repositorio.saveEvent(event);
        }).collect(Collectors.toList());
    }
}
