package com.FabricaAlimentoMascotas.ControlProduccion.domain;

import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Cliente;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.entitys.Compra;
import com.FabricaAlimentoMascotas.ControlProduccion.domain.values.ValorTotal;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.AggregateRoot;
import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

import java.time.LocalDateTime;

public class OrdenEntrega extends AggregateRoot<Identity> {

        protected Cliente cliente;
        protected Compra compra;
        protected LocalDateTime fechaCreacion;
        protected ValorTotal valorTotal;


        public OrdenEntrega(Identity id, Cliente cliente, Compra compra, LocalDateTime fechaCreacion, ValorTotal valorTotal) {
                super(id);
                this.cliente = cliente;
                this.compra = compra;
                this.fechaCreacion = fechaCreacion;
                this.valorTotal = valorTotal;
        }
}
