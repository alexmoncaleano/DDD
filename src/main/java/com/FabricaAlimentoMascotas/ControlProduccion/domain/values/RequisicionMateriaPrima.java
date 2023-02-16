package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.RequisicionProducto;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.ValueObject;

import java.util.*;

public class RequisicionMateriaPrima implements ValueObject<Set<MateriaPrima>> {

    private Set<MateriaPrima> requisicionMateriaPrima;

    public RequisicionMateriaPrima(Set<RequisicionProducto> requisicionProductos) {
        this.requisicionMateriaPrima = crearRequisicion(requisicionProductos);
    }

    public Set<MateriaPrima> crearRequisicion(Set<RequisicionProducto> listaRequisicion) {
        List<MateriaPrima> lista = new ArrayList<>();
        for (RequisicionProducto x : listaRequisicion) {
            Integer cantidad = x.cantidad().value().cantidad();
            for (MateriaPrima i : x.producto().consultarReceta().listaIngredientes()) {
                boolean existe = false;
                for (MateriaPrima mp : lista) {
                    if (mp.value().nombre().equals(i.value().nombre())) {
                        int cantidadNueva = (mp.value().cantidad().value().cantidad() + i.value().cantidad().value().cantidad()) * cantidad;
                        Cantidad cantidadObjNueva = new Cantidad(cantidadNueva, mp.value().cantidad().value().unidadMedida());
                        MateriaPrima mpNueva = new MateriaPrima(mp.value().nombre(), cantidadObjNueva);
                        lista.set(lista.indexOf(mp), mpNueva);
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    Cantidad cantidad1 = new Cantidad(i.value().cantidad().value().cantidad() * cantidad, i.value().cantidad().value().unidadMedida());
                    lista.add(new MateriaPrima(i.value().nombre(), cantidad1));
                }
            }
        }
        return new HashSet<>(lista);
    }

    @Override
    public Set<MateriaPrima> value() {
        return requisicionMateriaPrima;
    }
}


