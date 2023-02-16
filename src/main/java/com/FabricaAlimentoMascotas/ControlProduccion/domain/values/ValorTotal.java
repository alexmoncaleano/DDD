package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

public class ValorTotal implements ValueObject<ValorTotal.Props>{
    private Precio precioCompra;
    private BigDecimal impuestos;
    private Precio precioTotal;

    public ValorTotal(Precio precioCompra) {
        this.precioCompra = Objects.requireNonNull(precioCompra, "El precio de compra no puede ser nulo");
        this.impuestos = calcularIva();
        this.precioTotal = calcularPrecioTotal();
    }

    private BigDecimal calcularIva(){
        BigDecimal porcentajeIva = new BigDecimal("0.19");
        return precioCompra.value().precio().multiply(porcentajeIva);
    }

    private Precio calcularPrecioTotal(){
        return new Precio (precioCompra.value().precio().add(impuestos), precioCompra.value().moneda());
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Precio precioCompra() {
                return precioCompra;
            }

            @Override
            public BigDecimal impuestos() {
                return impuestos;
            }

            @Override
            public Precio precioTotal() {
                return precioTotal;
            }
        };
    }

    public interface Props{
        Precio precioCompra();
        BigDecimal impuestos();
        Precio precioTotal();
    }
}
