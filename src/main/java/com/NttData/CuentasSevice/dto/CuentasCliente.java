package com.NttData.CuentasSevice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class CuentasCliente {

    private ClienteInfo cliente;
    private List<CuentaInfo> cuentas;

    public CuentasCliente(ClienteInfo cliente, List<CuentaInfo> cuentas) {
        this.cliente = cliente;
        this.cuentas = cuentas;
    }

    public CuentasCliente() {
    }
}
