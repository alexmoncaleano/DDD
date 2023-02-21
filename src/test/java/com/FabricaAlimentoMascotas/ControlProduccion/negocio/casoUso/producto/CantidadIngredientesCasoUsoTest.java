package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit.KILOGRAMS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CantidadIngredientesCasoUsoTest {

    @Test
    public void agregarMateriaPrimaDeberiaGenerarEventoDeDominio() {
        // 1 Crear un Repositorio mock
        Repositorio repositorioMock = mock(Repositorio.class);
        Mockito.when(repositorioMock.saveEvent(Mockito.any())).thenAnswer(invocationOnMock -> {
            return (invocationOnMock.getArgument(0));
        });

        // 2 Creo el evento ProductoCreado
        ProductoCreado producto = new ProductoCreado(new Descripcion("Producto de prueba"),
                new Receta(RecetaId.of("RecetaCreada"),
                        new HashSet<MateriaPrima>()),new Stock(new HashSet<DetalleStock>()),new Informacion());
        producto.setAggregateRootId("123");
        List<DomainEvent> eventos = new ArrayList<>();
        eventos.add(producto);
        //3 Creo el evento MateriaPrimaAgregada
        MateriaPrimaCreada materiaPrimaCreada = new MateriaPrimaCreada(
                "Materia Prima Uno",
                new Cantidad(10, KILOGRAMS));
        materiaPrimaCreada.setAggregateRootId("123");
        eventos.add(materiaPrimaCreada);

        //4 Configurar el comportamiento del repositorio mock
        Mockito.when(repositorioMock.findById(Mockito.any())).thenReturn(eventos);

        //5 Creo mi caso de uso que recibe un evento en lugar de un comando
        CantidadIngredientesCasoUso cantidadIngredientesCasoUso = new CantidadIngredientesCasoUso(repositorioMock);

        //6 Ejecuto el caso de uso
        List<DomainEvent> eventos1 = cantidadIngredientesCasoUso.apply(materiaPrimaCreada);

        // Verificar que se guardó un evento ""
        assertEquals(1, eventos1.size());
        System.out.println(eventos1.get(0));
        assertEquals("numeroElementosCreado", eventos1.get(0).type);

        // Verificar que se llamó al método "findById" del repositorio con el id de producto correcto
        Mockito.verify(repositorioMock, Mockito.times(1)).findById("123");

        // Verificar que se llamó al método "saveEvent" del repositorio
        Mockito.verify(repositorioMock, Mockito.times(1)).saveEvent(Mockito.any());
    }




    }

