package com.FabricaAlimentoMascotas.ControlProduccion.domain.commands;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;

public class AgregarMateriaPrimaRecetaCommand extends Command {

    private String productoId;
    private String recetaId;
    private String nombre;

    //Cantidad
    private Integer cantidad;
    private WeightUnit unidadMedida;


    public AgregarMateriaPrimaRecetaCommand() {
    }



    public AgregarMateriaPrimaRecetaCommand(String of, String nombre, Integer cantidad, WeightUnit unidadMedida) {
        this.productoId = of;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(String recetaId) {
        this.recetaId = recetaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}