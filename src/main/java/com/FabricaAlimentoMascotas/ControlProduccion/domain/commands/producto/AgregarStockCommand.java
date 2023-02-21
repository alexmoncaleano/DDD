package com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.producto;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;

import java.math.BigDecimal;

public class AgregarStockCommand extends Command {

    private String productoId;
    private Integer unidades;
    //Cantidad
    private Integer cantidad;
    private WeightUnit unidadMedida;
    //Precio
    private BigDecimal precio;
    private Moneda moneda;

    public AgregarStockCommand(){}

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
