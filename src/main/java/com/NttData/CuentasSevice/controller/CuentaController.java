package com.NttData.CuentasSevice.controller;

import com.NttData.CuentasSevice.repository.CuentaCorrienteRepository;
import com.NttData.CuentasSevice.repository.CuentaRepositoryDAO;
import com.NttData.CuentasSevice.entity.Cuenta;
import com.NttData.CuentasSevice.services.CuentaServices;
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
    private CuentaCorrienteRepository cuentaRepository;
    @Autowired
    private CuentaServices cuentaServices;


    @GetMapping("/cuentas")
    public List<Cuenta> allCuentas(){
        return (List<Cuenta>) cuentaRepositoryDAO.findAll();
    }
    @GetMapping("/cuentas/{id}")
    public Optional<Cuenta> buscarPorId(@PathVariable("id") Integer id){
        return cuentaRepositoryDAO.findById(id);
    }
    @PostMapping("/cuentas")
    public Object createCuentas(@RequestBody Cuenta cuenta, BindingResult result) throws Exception {
        if (result.hasErrors()){
              throw new Exception("Fallida");
        }else {
            cuentaRepositoryDAO.save(cuenta);
//            cuentaServices.getClass().getName()+cuentaServices.setCuentasRepository(Cuenta ).depositar();
//            cuentaRepository.retiro(cuenta.getSaldo());
            return ResponseEntity.ok("Status Finalizada");

        }
    }


}
