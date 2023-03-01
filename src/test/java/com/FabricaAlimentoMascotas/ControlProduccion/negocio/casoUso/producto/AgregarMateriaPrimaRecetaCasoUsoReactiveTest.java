package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto.AgregarMateriaPrimaRecetaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.NumeroElementosCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto.AgregarMateriaPrimaRecetaCasoUsoReactive;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AgregarMateriaPrimaRecetaCasoUsoReactiveTest {

    @Test
    void testAgregarMateriaPrimaReceta() {
        // Crear un repositorio mock
        Repositorio repositorioMock = Mockito.mock(Repositorio.class);
        Mockito.when(repositorioMock.saveEvent(Mockito.any())).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return (DomainEvent) invocationOnMock.getArgument(0);
        });

        // Crear el evento ProductoCreado
        ProductoCreado producto = new ProductoCreado(new Descripcion("Producto de prueba"),
                new Receta(RecetaId.of("RecetaCreada"),
                        new HashSet<MateriaPrima>()),new Stock(new HashSet<DetalleStock>()),new Informacion());
        producto.setAggregateRootId("123");

        // Agregamos el evento a un flux de DomainEvent
        Flux<DomainEvent> eventosProducto = Flux.just(producto);
        // Configurar el comportamiento del repositorio mock

        Mockito.when(repositorioMock.findByIdReactive(Mockito.any())).thenReturn(eventosProducto);

        // Crear el caso de uso
        AgregarMateriaPrimaRecetaCasoUsoReactive agregarMateriaPrimaRecetaCasoUsoReactive = new AgregarMateriaPrimaRecetaCasoUsoReactive(repositorioMock);

        // Crear el comando
        AgregarMateriaPrimaRecetaCommand agregarMateriaPrimaRecetaCommand = new AgregarMateriaPrimaRecetaCommand();
        agregarMateriaPrimaRecetaCommand.setProductoId("123");
        agregarMateriaPrimaRecetaCommand.setNombre("Materia prima de prueba");
        agregarMateriaPrimaRecetaCommand.setCantidad(100);
        agregarMateriaPrimaRecetaCommand.setUnidadMedida(WeightUnit.GRAMS);

        // Ejecutar el caso de uso
        Flux<DomainEvent> eventos = agregarMateriaPrimaRecetaCasoUsoReactive.apply(Mono.just(agregarMateriaPrimaRecetaCommand));

        // Verificar que se guardó un evento "MateriaPrima.MateriaPrimaCreada"
        assertEquals(1, eventos.count().block());

        // Verificar que se llamó al método "findById" del repositorio con el id de producto correcto
        Mockito.verify(repositorioMock, Mockito.times(1)).findByIdReactive("123");

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(2)).saveEvent(Mockito.any());

        StepVerifier.create(eventos)
                .expectNextMatches(event -> event.getClass().equals(MateriaPrimaCreada.class))
                .expectNextMatches(event -> event.getClass().equals(NumeroElementosCreado.class))
                .expectComplete()
                .verify();
    }
}