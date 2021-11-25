package com.NttData.CuentasSevice.controller;

import com.NttData.CuentasSevice.Cuentas.CuentaCorrienteRepository;
import com.NttData.CuentasSevice.repository.CuentaRepositoryDAO;
import com.NttData.CuentasSevice.entity.Cuenta;
import com.NttData.CuentasSevice.services.CuentaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CuentaController {


    @Autowired
    private CuentaRepositoryDAO cuentaRepositoryDAO;

    @Autowired
    private CuentaServices cuentaServices;

    @GetMapping("/cuentas")
    public List<Cuenta> allCuentas(){
        return (List<Cuenta>) cuentaRepositoryDAO.findAll();
    }
    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> buscarPorId(@PathVariable("id") Integer id) throws CuentaInxistenteEception {
        Optional<Cuenta> optionalCuenta = cuentaRepositoryDAO.findById(id);
        if (optionalCuenta.isEmpty()){
            throw new CuentaInxistenteEception();
        }
        return ResponseEntity.ok(optionalCuenta.get());
    }
    @PostMapping("/cuentas")
    public Object createCuentas(@RequestBody Cuenta cuenta, BindingResult result) throws CuentaInxistenteEception {
        if (result.hasErrors()){
              throw new CuentaInxistenteEception();
        }else {
            cuentaRepositoryDAO.save(cuenta);
            return ResponseEntity.ok("Status Finalizada");

        }
    }


}
