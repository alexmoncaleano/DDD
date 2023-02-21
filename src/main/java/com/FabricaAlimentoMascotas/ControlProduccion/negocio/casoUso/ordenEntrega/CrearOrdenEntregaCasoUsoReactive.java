package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.OrdenEntrega;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.ordenEntrega.CrearOrdenEntregaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Cliente;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Compra;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.generic.UseCaseForCommandReactive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashSet;

public class CrearOrdenEntregaCasoUsoReactive extends UseCaseForCommandReactive<CrearOrdenEntregaCommand> {

    private Repositorio repositorio;
    public CrearOrdenEntregaCasoUsoReactive(Repositorio repositorio) {
        this.repositorio = repositorio;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<CrearOrdenEntregaCommand> crearOrdenEntregaCommandMono) {
        return crearOrdenEntregaCommandMono.flatMapIterable(command -> {
            OrdenEntrega ordenEntrega = new OrdenEntrega(OrdenEntregaId.of(command.getId()), new Cliente(ClienteId.of(command.getNumeroDocumento()),
                    new Nombre(command.getPrimerNombre(), command.getSegundoNombre(), command.getPrimerApellido(), command.getSegundoApellido()),
                    new Contacto(command.getCelular(), command.getEmail(), command.getDireccion()),
                    new Identificacion(command.getNumeroDocumento(), command.getTipoDocumento())), new Compra(new HashSet<Detalle>()), LocalDateTime.now(), true,
                    command.getProductoId(),
                    command.getUnidades(),
                    command.getCantidad(),
                    command.getUnidadMedida(),
                    command.getPrecio(),
                    command.getMoneda());
            return ordenEntrega.getUncommittedChanges();
        }).flatMap(domainEvent -> repositorio.saveEventReactive(domainEvent));
    }
}
