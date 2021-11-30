package com.NttData.CuentasSevice.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInfo {

    private String usuario;
    private String nombre;
    private String rfc;

    public ClienteInfo(String usuario, String nombre, String rfc) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.rfc = rfc;
    }

    public ClienteInfo() {
    }
}

