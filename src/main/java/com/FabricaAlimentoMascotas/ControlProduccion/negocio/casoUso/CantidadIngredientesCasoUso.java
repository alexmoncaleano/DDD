package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

public class CantidadIngredientesCasoUso implements UseCaseForEvent<MateriaPrimaCreada>{

    private Repositorio repositorio;

    public CantidadIngredientesCasoUso(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DomainEvent> apply(MateriaPrimaCreada domainEvent) {
        List<DomainEvent> events = repositorio.findById(domainEvent.aggregateRootId());
        if (events.size() > 0) {
            Producto producto = Producto.from(ProductoId.of(domainEvent.aggregateRootId()), events);
            producto.numeroIngredientes(producto.consultarReceta().listaIngredientes());
            return producto.getUncommittedChanges().stream().map(event -> {
                return repositorio.saveEvent(event);
            }).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("El Id no suministrado no corresponde a un producto existente");
        }
    }
}
