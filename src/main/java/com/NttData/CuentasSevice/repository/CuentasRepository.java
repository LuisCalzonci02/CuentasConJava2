package com.NttData.CuentasSevice.repository;

import com.NttData.CuentasSevice.entity.Cuenta;

public interface CuentasRepository {

    void depositar(double monto);
    void retiro(double monto);
    void account(double balance);
    double balance();





}
