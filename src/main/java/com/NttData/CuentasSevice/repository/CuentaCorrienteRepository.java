package com.NttData.CuentasSevice.repository;

import com.NttData.CuentasSevice.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CuentaCorrienteRepository implements CuentasRepository {

    @Autowired
    private CuentaRepositoryDAO cuentaRepositoryDAO;
    private double balance;

    public Optional<Cuenta> findSaldo() {
        return cuentaRepositoryDAO.findSaldo();
    }

    @Override
    public void depositar(double monto) {
        this.balance = balance + monto;
    }

    @Override
    public void retiro(double monto) {
        this.balance = balance - monto;
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
