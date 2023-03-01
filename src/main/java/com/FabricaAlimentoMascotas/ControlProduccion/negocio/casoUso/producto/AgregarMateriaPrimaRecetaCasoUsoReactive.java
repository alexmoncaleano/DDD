package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto.AgregarMateriaPrimaRecetaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.generic.UseCaseForCommandReactive;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AgregarMateriaPrimaRecetaCasoUsoReactive extends UseCaseForCommandReactive <AgregarMateriaPrimaRecetaCommand> {

    private Repositorio repositorio;

    public AgregarMateriaPrimaRecetaCasoUsoReactive(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AgregarMateriaPrimaRecetaCommand> agregarMateriaPrimaRecetaCommandMono) {
        return agregarMateriaPrimaRecetaCommandMono.flatMapMany(command -> repositorio.findByIdReactive(command.getProductoId())
                .collectList().flatMapIterable(domainEvents -> {
                    Producto producto = Producto.from(ProductoId.of(command.getProductoId()), domainEvents);
                    producto.agregarMateriaPrima(command.getNombre(),
                            new Cantidad(command.getCantidad(), command.getUnidadMedida()));
                    return producto.getUncommittedChanges();
                }).map(domainEvent -> repositorio.saveEvent(domainEvent))
        );
    }
}

