package com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Cliente;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Compra;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Detalle;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ValorTotal;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdenEntregaCreada extends DomainEvent {

    protected Cliente cliente;
    protected Compra compra;
    protected LocalDateTime fechaCreacion;
    protected ValorTotal valorTotal;
    protected Boolean estado;
    //DetalleCompra

    //DetalleCompra
    private String productoId;
    private Integer unidades;
    ////Presentacion
    private Integer cantidad;
    private WeightUnit unidadMedida;

    private BigDecimal precio;
    private Moneda moneda;


    public OrdenEntregaCreada(Cliente cliente, Compra compra, LocalDateTime fechaCreacion, Boolean estado, String productoId, Integer unidades, Integer cantidad, WeightUnit unidadMedida, BigDecimal precio, Moneda moneda) {
        super("OrdenEntregaCreada");
        this.cliente = cliente;
        this.compra = compra;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.productoId = productoId;
        this.unidades = unidades;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.moneda = moneda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ValorTotal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(ValorTotal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public WeightUnit getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(WeightUnit unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
