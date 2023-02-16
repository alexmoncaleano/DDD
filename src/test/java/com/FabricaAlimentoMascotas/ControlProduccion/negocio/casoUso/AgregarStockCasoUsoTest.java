package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.AgregarStockCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgregarStockCasoUsoTest {

    @Test
    void testAgregarStock() {
        // Crear un repositorio mock
        Repositorio repositorioMock = Mockito.mock(Repositorio.class);

        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.saveEvent(Mockito.any())).thenAnswer(invocationOnMock -> {
            // Retorna el evento que se le pasó como parámetro
            return (DomainEvent) invocationOnMock.getArgument(0);
        });

        // Crear el evento ProductoCreado
        ProductoCreado producto = new ProductoCreado( new Descripcion("Producto de prueba"),
                new Receta(RecetaId.of("RecetaCreada"),new HashSet<MateriaPrima>()),new Stock(new HashSet<DetalleStock>()),new Informacion());
        producto.setAggregateRootId("123");


        // Agregamos el evento a una lista de DomainEvent
        List<DomainEvent> eventosProducto = new ArrayList<>();
        eventosProducto.add(producto);

        // Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.findById(Mockito.any())).thenReturn(eventosProducto);

        // Crear el caso de uso
        AgregarStockCasoUso agregarStockCasoUso = new AgregarStockCasoUso(repositorioMock);

        // Crear el comando
        AgregarStockCommand agregarStockCommand = new AgregarStockCommand();
        agregarStockCommand.setProductoId("123");
        agregarStockCommand.setUnidades(5);
        agregarStockCommand.setCantidad(500);
        agregarStockCommand.setUnidadMedida(WeightUnit.GRAMS);
        agregarStockCommand.setPrecio(BigDecimal.valueOf(10));
        agregarStockCommand.setMoneda(Moneda.PESO);

        // Ejecutar el caso de uso
        List<DomainEvent> eventos = agregarStockCasoUso.apply(agregarStockCommand);

        // Verificar que se guardó un evento "Stock.DetalleStockCreado"
        assertEquals(1, eventos.size());
        assertEquals("DetalleStock.DetalleStockCreado", eventos.get(0).type);

        // Verificar que se llamó al método "findById" del repositorio con el id de producto correcto
        Mockito.verify(repositorioMock, Mockito.times(1)).findById("123");

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(1)).saveEvent(Mockito.any());
    }
}

