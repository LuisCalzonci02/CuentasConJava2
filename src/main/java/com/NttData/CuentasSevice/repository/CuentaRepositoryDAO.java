package com.NttData.CuentasSevice.repository;

import com.NttData.CuentasSevice.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CuentaRepositoryDAO extends CrudRepository<Cuenta, Integer> {

    @Query(value = "select * from Cuenta where saldo= :saldo", nativeQuery = true)
    public Optional<Cuenta> findSaldo();


}
