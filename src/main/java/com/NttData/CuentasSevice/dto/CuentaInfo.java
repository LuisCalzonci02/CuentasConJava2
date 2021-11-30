package com.NttData.CuentasSevice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaInfo {

    private Integer numeroCuenta;
    private String tipoCuenta;
    private String tipoMoneda;
    private String acuerdo;
    private float saldoCuenta;


    public CuentaInfo(Integer numeroCuenta, String tipoCuenta, String tipoMoneda, String acuerdo, float saldoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.tipoMoneda = tipoMoneda;
        this.acuerdo = acuerdo;
        this.saldoCuenta = saldoCuenta;
    }

    public CuentaInfo() {
    }
}
