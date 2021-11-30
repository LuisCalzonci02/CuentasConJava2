package com.NttData.CuentasSevice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PosicionCliente {
    ClienteInfo cliente;
    List<CuentaPosicion> cuentas;

    public PosicionCliente(ClienteInfo cliente, List<CuentaPosicion> cuentas) {
        this.cliente = cliente;
        this.cuentas = cuentas;
    }

    public PosicionCliente() {
    }
}
