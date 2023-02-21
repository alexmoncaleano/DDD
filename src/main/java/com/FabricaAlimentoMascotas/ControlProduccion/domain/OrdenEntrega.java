package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Cliente;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.Moneda;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.enums.WeightUnit;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.DetalleCompraCreado;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.*;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.eventos.ordenEntrega.OrdenEntregaCreada;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.AggregateRoot;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.DomainEvent;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenEntrega extends AggregateRoot<Identity> {

        protected Cliente cliente;
        protected Compra compra;
        protected LocalDateTime fechaCreacion;
        protected ValorTotal valorTotal;
        protected Boolean estado;


        public OrdenEntrega(Identity id, Cliente cliente, Compra compra, LocalDateTime fechaCreacion, Boolean estado,
                            String productoId, Integer unidades, Integer cantidad, WeightUnit unidadMedida, BigDecimal precio, Moneda moneda) {
                super(id);
                subscribe(new OrdenEntregaChange(this));
                appendChange(new OrdenEntregaCreada(cliente, compra, fechaCreacion, estado, productoId, unidades, cantidad, unidadMedida, precio, moneda));
        }

        private OrdenEntrega(OrdenEntregaId id) {
                super(id);
                subscribe(new OrdenEntregaChange(this));
        }

        public static OrdenEntrega from(OrdenEntregaId id, List<DomainEvent> events){
                OrdenEntrega ordenEntrega = new OrdenEntrega(id);
                events.forEach(event -> ordenEntrega.applyEvent(event));
                return ordenEntrega;
        }

        public void agregarDetalleCompra(Detalle detalle){
                appendChange(new DetalleCompraCreado(detalle.value().producto(), detalle.value().cantidad(), detalle.value().presentacion(), detalle.value().total())).apply();
        }
}
