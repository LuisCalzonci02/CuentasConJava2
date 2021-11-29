package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.Helper.MarcaTarjeta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TargetaDebito")
@Getter
@Setter
public class TarjetaDebito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTDebito;
    @Enumerated(EnumType.STRING)
    private MarcaTarjeta marcaTarjeta;
    private String estado;
    private String fechaVencimiento;
    private Float limiteExtraccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "FKnumeroCuenta", referencedColumnName = "id")
    private Cuenta FKnumeroCuenta;

}
