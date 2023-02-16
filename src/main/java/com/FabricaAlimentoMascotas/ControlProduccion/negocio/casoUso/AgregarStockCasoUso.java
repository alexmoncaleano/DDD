package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.AgregarStockCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

public class AgregarStockCasoUso implements UseCaseForCommand<AgregarStockCommand>{

    private Repositorio repositorio;

    public AgregarStockCasoUso(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DomainEvent> apply(AgregarStockCommand command) {
        List<DomainEvent> events = repositorio.findById(command.getProductoId());
        Producto producto = Producto.from(ProductoId.of(command.getProductoId()), events);
        producto.agregarStock(command.getUnidades(),
                new Presentacion(new Cantidad(command.getCantidad(), command.getUnidadMedida()),
                        new Precio(command.getPrecio(),command.getMoneda())));

        return producto.getUncommittedChanges().stream().map(event -> {
            return repositorio.saveEvent(event);
        }).collect(Collectors.toList());
    }
}
