package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.Objects;
import java.util.Set;

import static com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit.KILOGRAMS;
import static com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit.POUNDS;

public class Stock implements ValueObject<Stock.Props> {

    private Set<DetalleStock> listaStock;

    private Cantidad stockTotal;


    public Stock(Set<DetalleStock> listaStock) {
        this.listaStock = Objects.requireNonNull(listaStock);
        this.stockTotal = generarStockTotal(listaStock);
    }
    public Stock(){}

    public Cantidad generarStockTotal(Set<DetalleStock> lista){
        double total = 0.0;
        for (DetalleStock detalle : lista) {
            switch (detalle.value().presentacion().value().cantidad().value().unidadMedida()) {
                case POUNDS:
                    total += detalle.value().presentacion().value().cantidad().value().cantidad() / 2.204;
                    break;
                case KILOGRAMS:
                    total += detalle.value().presentacion().value().cantidad().value().cantidad();
                    break;
            }
        }
        if(total == 0){
            return new Cantidad();
        }else{
        return new Cantidad((int) total, KILOGRAMS);}
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Set<DetalleStock> listaStock() {
                return listaStock;
            }

            @Override
            public Cantidad stockTotal() {
                return stockTotal;
            }
        };
    }

    public interface Props {

        Set<DetalleStock> listaStock ();

        Cantidad stockTotal();

    }
}
