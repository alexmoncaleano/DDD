package com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.ordenEntrega;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Command;

import java.math.BigDecimal;

public class CrearDetalleCompraCommand extends Command {

    private String productoId;
    private Integer unidades;
    //presentacion
    ////cantidad
    private Integer cantidad;
    private WeightUnit unidadMedida;
    ////precio
    private BigDecimal precio;
    private Moneda moneda;

}
