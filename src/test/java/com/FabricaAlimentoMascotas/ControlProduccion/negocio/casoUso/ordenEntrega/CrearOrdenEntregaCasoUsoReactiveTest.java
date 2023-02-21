package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.ordenEntrega.CrearOrdenEntregaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.NumeroElementosCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.TipoDocumento.CEDULA;
import static org.junit.jupiter.api.Assertions.*;

class CrearOrdenEntregaCasoUsoReactiveTest {

    @Test
    void testCrearProducto() {
        // Crear un repositorio mock
        Repositorio repositorioMock = Mockito.mock(Repositorio.class);

        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.saveEventReactive(ArgumentMatchers.any(OrdenEntregaCreada.class))).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return Mono.just((DomainEvent) invocationOnMock.getArgument(0));
        });

        //Crear el caso de uso
        CrearOrdenEntregaCasoUsoReactive casoUso = new CrearOrdenEntregaCasoUsoReactive(repositorioMock);

        //Crear el comando
        CrearOrdenEntregaCommand command = new CrearOrdenEntregaCommand();
        command.setId("1");
        command.setCelular("3120000000");
        command.setNumeroDocumento("1234556641");
        command.setDireccion("calle de prueba");
        command.setEmail("prueba@gmail.com");
        command.setPrimerApellido("perez");
        command.setSegundoApellido("gallego");
        command.setPrimerNombre("karen");
        command.setSegundoNombre("jimena");
        command.setTipoDocumento(CEDULA);

        //Ejecutar el caso de Uso
        Flux<DomainEvent> eventos = casoUso.apply(Mono.just(command));


        StepVerifier.create(eventos)
                .expectNextMatches(event -> {
                    event.getClass().equals(OrdenEntregaCreada.class);
                    OrdenEntregaCreada ordenEntrega = (OrdenEntregaCreada) event;
                    assertEquals(ordenEntrega.getCliente().getNombre().value().primerNombre(), command.getPrimerNombre());
                    return true;
                })
                .expectComplete()
                .verify();

        //assertEquals(1, eventos.count().block());




        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(1)).saveEventReactive(ArgumentMatchers.any(OrdenEntregaCreada.class));
    }
}