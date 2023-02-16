package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.CrearProductoCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CrearProductoCasoUsoTest {

    @Test
    void testCrearProducto() {
        // Crear un repositorio mock
        Repositorio repositorioMock = Mockito.mock(Repositorio.class);

        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.saveEvent(Mockito.any())).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return (DomainEvent) invocationOnMock.getArgument(0);
        });

        // Crear el caso de uso
        CrearProductoCasoUso crearProductoCasoUso = new CrearProductoCasoUso(repositorioMock);

        // Crear el comando
        CrearProductoCommand crearProductoCommand = new CrearProductoCommand();
        crearProductoCommand.setId("123");
        crearProductoCommand.setDescripcion("Producto de prueba");
        crearProductoCommand.setRecetaId("456");

        // Ejecutar el caso de uso
        List<DomainEvent> eventos = crearProductoCasoUso.apply(crearProductoCommand);

        // Verificar que se guardó un evento "ProductoCreado"
        assertEquals(1, eventos.size());
        assertEquals("Producto.productoCreado", eventos.get(0).type);

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(1)).saveEvent(Mockito.any());
    }
}

