package com.NttData.CuentasSevice.dto;

import com.example.EquipoC.dto.InversionesInfo;
import com.nttData.TarjetasDebito.entity.TarjetaDebito;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CuentaPosicion {
    CuentaInfo cuenta;
    List<TarjetaDebito> Tarjetas;
    List<InversionesInfo> Inversiones;
   // List<> Prestamos;


    public CuentaPosicion(CuentaInfo cuenta, List<TarjetaDebito> tarjetas) {
        this.cuenta = cuenta;
        Tarjetas = tarjetas;
    }

    public CuentaPosicion() {
    }
}
