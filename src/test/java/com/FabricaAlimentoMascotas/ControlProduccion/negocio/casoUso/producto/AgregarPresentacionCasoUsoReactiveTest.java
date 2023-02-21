package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto.AgregarPresentacionCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.PresentacionCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto.AgregarPresentacionCasoUsoReactive;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AgregarPresentacionCasoUsoReactiveTest {
    @Test
    public void agregarPresentacionDeberiaCrearEventoDeDominio() {
        // Paso 1: Crear un objeto Repositorio mock
        Repositorio repositorioMock = mock(Repositorio.class);
        Mockito.when(repositorioMock.saveEventReactive(Mockito.any())).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return Mono.just((DomainEvent) invocationOnMock.getArgument(0));
        });

        //crearEvento
        ProductoCreado producto = new ProductoCreado(new Descripcion("Producto de prueba"),
                new Receta(RecetaId.of("RecetaCreada"),
                        new HashSet<MateriaPrima>()),new Stock(new HashSet<DetalleStock>()),new Informacion());
        producto.setAggregateRootId("123");

        // Agregamos el evento a un flux de DomainEvent
        Flux<DomainEvent> eventosProducto = Flux.just(producto);
        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.findByIdReactive(Mockito.any())).thenReturn(eventosProducto);

        // Paso 2: Crear un objeto AgregarPresentacionCommand válido
        String productoId = "123";
        Integer cantidad = 10;
        WeightUnit unidadMedida = WeightUnit.KILOGRAMS;
        BigDecimal precio = BigDecimal.valueOf(10.500);
        Moneda moneda = Moneda.PESO;
        AgregarPresentacionCommand agregarPresentacionCommand = new AgregarPresentacionCommand(productoId, cantidad, unidadMedida, precio, moneda);

        // Paso 3: Crear una instancia de AgregarPresentacionCasoUsoReactive
        AgregarPresentacionCasoUsoReactive agregarPresentacionCasoUsoReactive = new AgregarPresentacionCasoUsoReactive(repositorioMock);

        Flux<DomainEvent> eventos = agregarPresentacionCasoUsoReactive.apply(Mono.just(agregarPresentacionCommand));

        assertEquals(1, eventos.count().block());

        // Verificar que se llamó al método "findByIdReactive" del repositorio con el id de producto correcto
        Mockito.verify(repositorioMock, Mockito.times(1)).findByIdReactive("123");

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(1)).saveEventReactive(Mockito.any());

        StepVerifier.create(eventos)
                .expectNextMatches(event -> event.getClass().equals(PresentacionCreada.class))
                .expectComplete()
                .verify();

    }

}