package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.AgregarMateriaPrimaRecetaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ProductoCreado;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class AgregarMateriaPrimaRecetaCasoUsoTest {

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

        // Agregamos el evento a una lista de DomainEvent
        List<DomainEvent> eventosProducto = new ArrayList<>();
        eventosProducto.add(producto);

        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.findById(Mockito.any())).thenReturn(eventosProducto);

        // Crear el caso de uso
        AgregarMateriaPrimaRecetaCasoUso agregarMateriaPrimaRecetaCasoUso = new AgregarMateriaPrimaRecetaCasoUso(repositorioMock);

        // Crear el comando
        AgregarMateriaPrimaRecetaCommand agregarMateriaPrimaRecetaCommand = new AgregarMateriaPrimaRecetaCommand();
        agregarMateriaPrimaRecetaCommand.setProductoId("123");
        agregarMateriaPrimaRecetaCommand.setNombre("Materia prima de prueba");
        agregarMateriaPrimaRecetaCommand.setCantidad(100);
        agregarMateriaPrimaRecetaCommand.setUnidadMedida(WeightUnit.GRAMS);

        // Ejecutar el caso de uso
        List<DomainEvent> eventos = agregarMateriaPrimaRecetaCasoUso.apply(agregarMateriaPrimaRecetaCommand);

        // Verificar que se guardó un evento "MateriaPrima.MateriaPrimaCreada"
        assertEquals(2, eventos.size());
        assertEquals("MateriaPrima.MateriaPrimaCreada", eventos.get(0).type);
        assertEquals("numeroElementosCreado", eventos.get(1).type);

        // Verificar que se llamó al método "findById" del repositorio con el id de producto correcto
        Mockito.verify(repositorioMock, Mockito.times(1)).findById("123");

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(2)).saveEvent(Mockito.any());
    }
}