package com.NttData.CuentasSevice.repository;

public interface CuentasRepository {

    void depositar(double monto);
    void retiro(double monto);
    void account(double balance);
    double balance();


}
