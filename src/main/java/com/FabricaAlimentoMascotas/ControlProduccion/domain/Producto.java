package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.DetalleStockCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.MateriaPrimaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ProductoCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.numeroElementosCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.AggregateRoot;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Producto extends AggregateRoot<ProductoId> {

    protected Descripcion descripcion;
    protected Receta receta;
    protected Stock stock;
    protected Informacion informacion;
    protected Integer elementosReceta;

    public Producto(ProductoId id, Descripcion descripcion, Receta receta, Stock stock, Informacion informacion) {
        super(id);
        subscribe(new ProductoChange(this));
        appendChange(new ProductoCreado(descripcion, receta, stock, informacion)).apply();
    }

    private Producto(ProductoId id) {
        super(id);
        subscribe(new ProductoChange(this));
    }

    public static Producto from(ProductoId id, List<DomainEvent> events){
        Producto producto = new Producto(id);
        events.forEach(event -> producto.applyEvent(event));
        return producto;
    }

    public void agregarMateriaPrima(String nombre, Cantidad cantidad){
        appendChange(new MateriaPrimaCreada(nombre, cantidad)).apply();
    }
    public void agregarStock(Integer unidades, Presentacion presentacion){
        appendChange(new DetalleStockCreado(unidades, presentacion)).apply();
    }
    public Receta consultarReceta(){
        return this.receta;
    }

    public void numeroIngredientes(Set<MateriaPrima> receta){
        appendChange(new numeroElementosCreado(receta)).apply();
    }
}
