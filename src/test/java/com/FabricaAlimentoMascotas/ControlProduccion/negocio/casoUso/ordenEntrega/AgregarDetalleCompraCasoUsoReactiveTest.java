package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Cliente;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.TipoDocumento;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.DetalleCompraCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class AgregarDetalleCompraCasoUsoReactiveTest {

    @Test
    void successfulScenario() {

        //1) creo el evento
        OrdenEntregaCreada event = new OrdenEntregaCreada(
                new Cliente(ClienteId.of("1024000000"),
                        new Nombre("PrimerNombrePrueba", "SegundoNombrePrueba", "PrimerApellidoPrueba", "SegundoApellidoPrueba"),
                        new Contacto("3000000000", "Email@prueba.com", "Calle Prueba # Prueba - 1"),
                        new Identificacion("1024000000", TipoDocumento.CEDULA)),
                new Compra(
                        new HashSet<>())
                , LocalDateTime.now(),
                true,
                "123",
                500,
                1,
                WeightUnit.KILOGRAMS,
                BigDecimal.valueOf(20000),
                Moneda.PESO);

        //Mockeo el repositorio
        Repositorio repositorioMock = mock(Repositorio.class);
        Mockito.when(repositorioMock.saveEventReactive(ArgumentMatchers.any(DetalleCompraCreado.class))).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return Mono.just((DomainEvent) invocationOnMock.getArgument(0));
        });

        //Se inicializa el caso de uso
        AgregarDetalleCompraCasoUsoReactive casoUso = new AgregarDetalleCompraCasoUsoReactive(repositorioMock);

        Flux<DomainEvent> event1 = casoUso.apply(Mono.just(event));

        StepVerifier.create(event1)
                .expectNextMatches(domainEvent -> {
                    domainEvent.getClass().equals(MateriaPrimaCreada.class);
                    DetalleCompraCreado detalle = (DetalleCompraCreado) domainEvent;
                    Assertions.assertEquals(detalle.getCantidad(), event.getUnidades());
                    return true;
                })
                .expectComplete()
                .verify();

        Mockito.verify(repositorioMock, Mockito.times(1)).saveEventReactive(ArgumentMatchers.any(DetalleCompraCreado.class));

    }
}