package com.NttData.CuentasSevice.services;

import com.NttData.CuentasSevice.dto.*;
import com.NttData.CuentasSevice.entity.Cuenta;
import com.NttData.CuentasSevice.repository.CuentaRepositoryDAO;
import com.NttData.CuentasSevice.repository.CuentasRepository;
import com.example.EquipoC.dto.InversionesCliente;
import com.example.EquipoC.dto.InversionesInfo;
import com.example.EquipoC.entity.Cliente;
import com.example.EquipoC.entity.Inversiones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServices {

    @Qualifier("cuentaCorrienteRepository")

    @Autowired
    private CuentasRepository cuentasRepository;

    @Autowired
    private CuentaRepositoryDAO cuentaRepositoryDao;

    public boolean obtenerAutentificacion(int id) {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/apiCliente/getAutentificacion/" + id, Boolean.class);
    }

    public boolean obtenerAuntentificacionPorCuenta(Integer numCuenta) {
        Optional<Cuenta> cuenta = cuentaRepositoryDao.findCuentaByNumCuenta(numCuenta);

        if (cuenta.isPresent()) {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject("http://localhost:8081/apiCliente/getAutentificacion/" + cuenta.get().getIdcliente(), Boolean.class);
        } else {
            return false;
        }


    }

    public void actualizarCuenta (Cuenta cuenta){
        cuentaRepositoryDao.save(cuenta);
    }

    public List<Cuenta> getCuentas(int id) {
        return cuentaRepositoryDao.findCuentaById(id);
    }

    public CuentasCliente getCuentasCliente(int id) {
        Cliente c = obtenerInfoCliente(id);

        ClienteInfo clienteInfo = new ClienteInfo(c.getUsuario(), c.getNombre() + " " + c.getApellido(), c.getRfc());

        CuentasCliente cuentasCliente = new CuentasCliente(clienteInfo, generarLista(getCuentas(id)));
        return cuentasCliente;
    }

    private List<CuentaInfo> generarLista(List<Cuenta> cuentas) {
        List<CuentaInfo> cuentasInfo = new ArrayList<>();

        for (Cuenta c : cuentas) {
            if (c.getTipoCuenta().equals("Cuenta Ahorro")) {
                CuentaInfo cuentaInfo = new CuentaInfo(c.getNumeroCuenta(), c.getTipoCuenta(), c.getTipoMoneda().toString(), "No aplica", c.getSaldo());
                cuentasInfo.add(cuentaInfo);
            } else {
                CuentaInfo cuentaInfo = new CuentaInfo(c.getNumeroCuenta(), c.getTipoCuenta(), c.getTipoMoneda().toString(), String.valueOf(c.getAcuerdo()), c.getSaldo());
                cuentasInfo.add(cuentaInfo);
            }
        }
        return cuentasInfo;
    }

    public Cliente obtenerInfoCliente(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/apiCliente/traerInfoCliente/" + id, Cliente.class);
    }


    public boolean cuentaExiste(int numCuenta) {
        return cuentaRepositoryDao.findCuentaByNumCuenta(numCuenta).isPresent();
    }

    public CuentasRepository getCuentasRepository() {
        return cuentasRepository;
    }

    public void setCuentasRepository(CuentasRepository cuentasRepository) {
        this.cuentasRepository = cuentasRepository;
    }


    public PosicionCliente getPosicionCliente(int id) {
        Cliente c = obtenerInfoCliente(id);
        ClienteInfo clienteInfo = new ClienteInfo(c.getUsuario(), c.getNombre() + " " + c.getApellido(), c.getRfc());
        return new PosicionCliente(clienteInfo, generarListaCuenta(getCuentas(id)));

    }

    private List<CuentaPosicion> generarListaCuenta(List<Cuenta> cuentas) {
        RestTemplate restTemplate = new RestTemplate();

        List<CuentaPosicion> cuentasPosicion = new ArrayList<>();



        for (Cuenta c : cuentas) {
            String acuerdo="";
            if(c.getTipoCuenta().equals("Cuenta Ahorro")){
                acuerdo = "No aplica";
            }else{
                acuerdo = String.valueOf(c.getAcuerdo()) ;
            }

            List tarjetas = restTemplate.getForObject("http://localhost:8088/tarjetasCuenta/" +c.getNumeroCuenta(), List.class);
            List inversionesC = restTemplate.getForObject("http://localhost:8082/inversionesCliente/" +c.getNumeroCuenta(), List.class);

            CuentaPosicion cp = new CuentaPosicion();
            cp.setTarjetas(tarjetas);
            cp.setInversiones(inversionesC);
            cuentasPosicion.add(cp);
            cp.setCuenta(new CuentaInfo(c.getNumeroCuenta(), c.getTipoCuenta(), c.getTipoMoneda().toString(),acuerdo, c.getSaldo()));
        }

        return cuentasPosicion;
    }


    public InversionesCliente getInversionesCliente(int id) {
        List<Cuenta> cuentas= cuentaRepositoryDao.findCuentaById(id);
        Cliente c = obtenerInfoCliente(id);
        ClienteInfo clienteInfo = new ClienteInfo(c.getUsuario(), c.getNombre() + " " + c.getApellido(), c.getRfc());

        return new InversionesCliente(clienteInfo,generarListaInversiones(cuentas));

    }

    private  List<List<InversionesInfo>> generarListaInversiones(List<Cuenta> cuentas) {
        List<List<InversionesInfo>> inversionesInfo = new ArrayList<>();

         RestTemplate restTemplate = new RestTemplate();

        for (Cuenta c: cuentas) {
            List inversionesC = restTemplate.getForObject("http://localhost:8082/inversionesCliente/" +c.getNumeroCuenta(), List.class);

            inversionesInfo.add(inversionesC);
        }

        return inversionesInfo;
    }
}