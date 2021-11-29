package com.NttData.CuentasSevice.controller;

import com.NttData.CuentasSevice.entity.TarjetaDebito;
import com.NttData.CuentasSevice.repository.TarjetaDebitoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TarjetaDebitoController<finañ> {




    @Autowired
    private  TarjetaDebitoDAO tarjetaDebitoDAO;
    @GetMapping("/tarjetas")
    public List<TarjetaDebito> allTarjetasDebito(){
        return (List<TarjetaDebito>) tarjetaDebitoDAO.findAll();
    }

    @PostMapping("/tarjeta")
    public TarjetaDebito solicitudTarjetaDebito(@RequestBody TarjetaDebito tarjetaDebito){
        return tarjetaDebitoDAO.save(tarjetaDebito);
    }

    @PutMapping("/tarjeta/{id}")
        public TarjetaDebito updateTarjeta(@PathVariable Integer id ,@RequestBody TarjetaDebito tarjetaDebito) {
        return tarjetaDebitoDAO.save(tarjetaDebito);
    }

    @DeleteMapping("/tarjeta/{id}")
    public void deleteTarjeta(@PathVariable("id") Integer id) {
        tarjetaDebitoDAO.deleteById(id);
    }
}
