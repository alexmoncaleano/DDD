package com.FabricaAlimentoMascotas.ControlProduccion.negocio.casoUso;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.Producto;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.commands.AgregarMateriaPrimaRecetaCommand;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.Cantidad;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.MateriaPrima;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ProductoId;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.negocio.gateways.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

public class AgregarMateriaPrimaRecetaCasoUso implements UseCaseForCommand<AgregarMateriaPrimaRecetaCommand>{

    private Repositorio repositorio;
    public AgregarMateriaPrimaRecetaCasoUso(Repositorio repositorio){this.repositorio = repositorio;}

    @Override
    public List<DomainEvent> apply(AgregarMateriaPrimaRecetaCommand command) {
        List<DomainEvent> events = repositorio.findById(command.getProductoId());
        if(events.size()>0){
        Producto producto = Producto.from(ProductoId.of(command.getProductoId()), events);
        producto.agregarMateriaPrima(command.getNombre(),
                new Cantidad(command.getCantidad(), command.getUnidadMedida()));
            producto.numeroIngredientes(producto.consultarReceta().listaIngredientes());
            return producto.getUncommittedChanges().stream().map(event -> {
                return repositorio.saveEvent(event);
            }).collect(Collectors.toList());
        }else{
            throw new IllegalArgumentException("El Id no suministrado no corresponde a un producto existente");
        }

    }
}
