package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto.AgregarPresentacionCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Precio;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.generic.UseCaseForCommandReactive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarPresentacionCasoUsoReactive extends UseCaseForCommandReactive<AgregarPresentacionCommand> {

    private Repositorio repositorio;

    public AgregarPresentacionCasoUsoReactive(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AgregarPresentacionCommand> agregarPresentacionCommandMono) {
        return agregarPresentacionCommandMono.flatMapMany(command -> repositorio.findByIdReactive(command.getProductoId())
                .collectList().flatMapIterable(domainEvents -> {
                    Producto producto = Producto.from(ProductoId.of(command.getProductoId()), domainEvents);
                    producto.agregarPresentacion(new Cantidad(command.getCantidad(), command.getUnidadMedida()),new Precio(command.getPrecio(),command.getMoneda()));
                    return producto.getUncommittedChanges();
                }).flatMap(domainEvent -> repositorio.saveEventReactive(domainEvent))
        );
    }
}


