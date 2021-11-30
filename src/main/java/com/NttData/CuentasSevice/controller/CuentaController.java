package com.NttData.CuentasSevice.controller;

import com.NttData.CuentasSevice.config.exception.CuentaInxistenteEception;
import com.NttData.CuentasSevice.config.exception.NoCuentasException;
import com.NttData.CuentasSevice.config.exception.NoLoginException;
import com.NttData.CuentasSevice.dto.CuentasCliente;
import com.NttData.CuentasSevice.dto.PosicionCliente;
import com.NttData.CuentasSevice.repository.CuentaRepositoryDAO;
import com.NttData.CuentasSevice.entity.Cuenta;
import com.NttData.CuentasSevice.services.CuentaServices;
import com.example.EquipoC.dto.InversionesCliente;
import com.example.EquipoC.entity.Cliente;
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
    public List<Cuenta> allCuentas() {
        return (List<Cuenta>) cuentaRepositoryDAO.findAll();
    }

    @GetMapping("/cuenta/{id}")
    public Cuenta buscarPorId(@PathVariable("id") Integer id) throws CuentaInxistenteEception {
        Optional<Cuenta> optionalCuenta = cuentaRepositoryDAO.findById(id);
        if (optionalCuenta.isEmpty()) {
            throw new CuentaInxistenteEception();
        }
        return optionalCuenta.get();
    }

    @GetMapping("/cuentaPorNumCuenta/{num}")
    public Cuenta buscarPorNumCuenta(@PathVariable("num") Integer num) throws CuentaInxistenteEception {
        Optional<Cuenta> optionalCuenta = cuentaRepositoryDAO.findCuentaByNumCuenta(num);

        return optionalCuenta.get();
    }


    @PostMapping("/abrirCuenta")
    public ResponseEntity<String> createCuentas(@RequestBody Cuenta cuenta, BindingResult result) throws CuentaInxistenteEception {
        Optional<Cuenta> optionalCuenta = cuentaRepositoryDAO.findCuentaByNumCuenta(cuenta.getNumeroCuenta());

        if (cuentaServices.obtenerAutentificacion(cuenta.getIdcliente())) {
            if (optionalCuenta.isEmpty()) {
                if (result.hasErrors()) {
                    throw new CuentaInxistenteEception();
                } else {
                    cuentaRepositoryDAO.save(cuenta);
                    return ResponseEntity.ok("Status Finalizada: Apertura de cuenta exitosa.");
                }
            } else {
                return ResponseEntity.status(400).body("El número de cuenta proporcionado ya está asociado a un cliente.");

            }
        } else {
            return ResponseEntity.status(401).body("Debe de iniciar sesión");
        }
    }


    @GetMapping("/cuentasCliente/{id}")
    public CuentasCliente findCuentaById(@PathVariable("id") int id) throws NoLoginException, NoCuentasException {
        List<Cuenta> cuentas = cuentaServices.getCuentas(id);

        if (cuentaServices.obtenerAutentificacion(id)) {
            if (cuentas.size() != 0) {
                return cuentaServices.getCuentasCliente(id);
            } else {
                throw new NoCuentasException();
            }
        } else {
            throw new NoLoginException();

        }
    }

   @GetMapping("/inversionesCliente/{id}")
    public InversionesCliente inversionesCliente(@PathVariable("id") int id) throws NoLoginException, NoCuentasException {
        return  cuentaServices.getInversionesCliente(id);
    }


    @GetMapping("/traerCliente/{id}")
    public Cliente findCliente(@PathVariable("id") Integer id) {
        return cuentaServices.obtenerInfoCliente(id);
    }

    @PutMapping("/actualizarSaldo")
    public void actualizarSaldo(@RequestBody Cuenta cuenta) {
        cuentaServices.actualizarCuenta(cuenta);
    }

    @GetMapping("/cuentaExiste/{num}")
    public boolean findCuenta(@PathVariable("num") Integer num) {
        return cuentaServices.cuentaExiste(num);

    }
    //hola mundo :)
    @GetMapping("/autentificacionPorCuenta/{num}")
    public boolean getAutentificacionPorCuenta(@PathVariable("num") int num) {
        return cuentaServices.obtenerAuntentificacionPorCuenta(num);
    }

    @GetMapping("/posicionCliente/{id}")
    public PosicionCliente getPosicion(@PathVariable("id") int id) throws NoLoginException {

        if (cuentaServices.obtenerAutentificacion(id)) {
            return cuentaServices.getPosicionCliente(id);
        } else {
            throw new NoLoginException();
        }


    }
}