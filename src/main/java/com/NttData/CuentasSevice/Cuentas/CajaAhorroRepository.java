package com.NttData.CuentasSevice.Cuentas;

import com.NttData.CuentasSevice.repository.CuentasRepository;

public class CajaAhorroRepository implements CuentasRepository {
    private double balance;
    @Override
    public void depositar(double monto) {
        this.balance = balance + monto;
    }

    @Override
    public void retiro(double monto) {
        if (this.balance  > monto){
            balance = balance - monto;
        }else {
            System.out.println("el monto es insuficiente");
        }
    }

    @Override
    public void account(double balance) {
        this.balance = balance;
    }

    @Override
    public double balance() {
        return this.balance;
    }
}
