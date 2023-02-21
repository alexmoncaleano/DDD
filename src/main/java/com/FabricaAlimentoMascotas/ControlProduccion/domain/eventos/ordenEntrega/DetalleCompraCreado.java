package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Precio;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Presentacion;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.util.Objects;

public class DetalleCompraCreado extends DomainEvent {

    private ProductoId producto;
    private Integer cantidad;
    private Presentacion presentacion;
    private Precio precio;

    public DetalleCompraCreado(ProductoId producto, Integer cantidad, Presentacion presentacion, Precio precio) {
        super("DetalleCompraCreado");
        this.producto = Objects.requireNonNull(producto, "El producto no puede ser nulo");
        this.cantidad = Objects.requireNonNull(cantidad, "La cantidad no puede ser nula");
        this.presentacion = Objects.requireNonNull(presentacion, "La presentacion no puede ser nula");
        this.precio = Objects.requireNonNull(precio, "El precio no puede ser nulo");
    }

    public ProductoId getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public Precio getPrecio() {
        return precio;
    }
}
