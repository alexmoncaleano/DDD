package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Receta;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Descripcion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Informacion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Stock;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

public class ProductoCreado extends DomainEvent {

    private final Descripcion descripcion;
    private final Receta receta;
    private final Stock stock;
    private final Informacion informacion;

    public ProductoCreado(Descripcion descripcion, Receta receta, Stock stock, Informacion informacion) {
        super("Producto.productoCreado");
        this.descripcion = descripcion;
        this.receta = receta;
        this.stock = stock;
        this.informacion = informacion;
    }

    public Descripcion getDescripcion(){return descripcion;}
    public Receta getReceta(){return receta;}
    public Stock getStock(){return stock;}
    public Informacion getInformacion(){return informacion;}
}
