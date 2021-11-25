package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.repository.CuentasRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CuentaCorriente extends Cuenta implements CuentasRepository {

    private float numeroCuenta;
    private float acuerdo;
    private double monto;

    private double balance;

    @Override
    public void depositar(double monto) {
        this.balance = balance + monto;
    }

    @Override
    public void retiro(double monto) {
        if ((balance + acuerdo) < monto){
            this.balance = balance - monto;
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
