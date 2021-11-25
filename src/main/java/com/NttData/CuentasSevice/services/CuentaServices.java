package com.NttData.CuentasSevice.services;

import com.NttData.CuentasSevice.repository.CuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CuentaServices {

@Qualifier("cuentaCorrienteRepository")

    @Autowired
    private CuentasRepository cuentasRepository;

    public CuentasRepository getCuentasRepository() {
        return cuentasRepository;
    }

    public void setCuentasRepository(CuentasRepository cuentasRepository) {
        this.cuentasRepository = cuentasRepository;
    }
}
