package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.repository.CuentasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CuentaAhorro extends Cuenta implements CuentasRepository {
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
