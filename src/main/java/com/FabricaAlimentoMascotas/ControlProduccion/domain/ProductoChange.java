package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.DetalleCompraCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.producto.*;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.EventChange;

import java.util.HashSet;

public class ProductoChange extends EventChange {


    public ProductoChange(Producto producto) {

        apply(((ProductoCreado event) -> {
            producto.stock = event.getStock();
            producto.informacion = event.getInformacion();
            producto.descripcion = event.getDescripcion();
            producto.receta = event.getReceta();
        }));

        apply((RecetaCreada event)-> {
            Receta receta = new Receta(event.getRecetaId(), new HashSet<MateriaPrima>());
            producto.receta = receta;
        });

        apply((MateriaPrimaCreada event) -> {
            MateriaPrima materiaPrima = new MateriaPrima(event.getNombre(), event.getCantidad());
            producto.receta.listaIngredientes().add(materiaPrima);
        });

        apply((NumeroElementosCreado event) -> {
            Integer numeroIngredientes = event.getCantidadIngredientes();
            producto.elementosReceta = numeroIngredientes;
        });

        apply((StockCreado event)->{
            Stock stock = new Stock(new HashSet<DetalleStock>());
            producto.stock = stock;
        });

        apply((DetalleStockCreado event)->{
           DetalleStock detalle = new DetalleStock(event.getUnidades(), event.getPresentacion());
           producto.stock.value().listaStock().add(detalle);
        });

        apply((InformacionCreada event)->{
            Informacion informacion = new Informacion(new HashSet<Presentacion>());
            producto.informacion = informacion;
        });

        apply((PresentacionCreada event)->{
            Presentacion presentacion = new Presentacion(event.getCantidad(),event.getPrecio());
            producto.informacion.getPresentaciones().add(presentacion);
        });
    }
}
