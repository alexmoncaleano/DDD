package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.OrdenEntrega;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.generic.UseCaseForEventReactive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class AgregarDetalleCompraCasoUsoReactive extends UseCaseForEventReactive<OrdenEntregaCreada> {

    private Repositorio repositorio;

    public AgregarDetalleCompraCasoUsoReactive(Repositorio repositorio) {
        this.repositorio = repositorio;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<OrdenEntregaCreada> ordenEntregaCreadaMono) {
        return ordenEntregaCreadaMono.flatMapIterable(event -> {
            OrdenEntrega ordenEntrega = OrdenEntrega.from(OrdenEntregaId.of("OrdenEntregaCreada"), List.of(event));
            Detalle detalle =
                    new Detalle(ProductoId.of(event.getProductoId()),
                    event.getUnidades(),
                            new Presentacion(
                                    new Cantidad(event.getCantidad(),
                                            event.getUnidadMedida()),
                                    new Precio(event.getPrecio(),
                                            event.getMoneda())));
            ordenEntrega.agregarDetalleCompra(detalle);
            return ordenEntrega.getUncommittedChanges();
        }).flatMap(domainEvent -> repositorio.saveEventReactive(domainEvent));
    }
}
