package com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Detalle;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Precio;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Entity;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Compra extends Entity<Identity> {
    private Set<Detalle> detallesCompra;
    private Precio precioTotal;

    public Compra(Identity id, Set<Detalle> detallesCompra) {
        super(id);
        validateDetallesCompra(detallesCompra);
        this.detallesCompra = detallesCompra;
        this.precioTotal = calcularPrecioTotal();
    }

    private void validateDetallesCompra(Set<Detalle> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException("La compra debe tener al menos un detalle");
        }
        if (detalles.contains(null)) {
            throw new IllegalArgumentException("Los detalles no pueden ser nulos");
        }
    }

    private Precio calcularPrecioTotal() {
        BigDecimal total = BigDecimal.ZERO;
        Moneda tipoMoneda = null;
        for (Detalle detalle : detallesCompra) {
            if (tipoMoneda == null) {
                tipoMoneda = detalle.value().total().value().moneda();
            } else if (!tipoMoneda.equals(detalle.value().total().value().moneda())) {
                throw new IllegalArgumentException("Todos los detalles deben tener la misma moneda");
            }
            total = total.add(detalle.value().total().value().precio());
        }
        return new Precio(total, tipoMoneda);
    }
}
