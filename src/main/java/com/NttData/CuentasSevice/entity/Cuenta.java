package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.Helper.TipoMoneda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Cuentas")
@Getter @Setter
public class Cuenta {
//hola
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
//  private Integer idCliente;
  @Column(nullable = false, unique = true)
  private Integer numerooCuenta;
  private Float saldo;
  @Enumerated(EnumType.STRING)
  private TipoMoneda tipoMoneda;
  private String tipoCuenta;

  @JsonIgnore
  @OneToOne(mappedBy = "FKnumeroCuenta")
  private TarjetaDebito tarjetaDebito;


}
