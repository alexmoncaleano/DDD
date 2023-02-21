package com.FabricaAlimentoMascotas.ControlProduccion.domain.values;

import com.FabricaAlimentoMascotas.ControlProduccion.generic.Identity;

public class ClienteId extends Identity{



        public ClienteId(String uuid) {
            super(uuid);
        }

        public ClienteId(){

        }

        public static ClienteId of(String uuid) {return new ClienteId(uuid);}

    }



