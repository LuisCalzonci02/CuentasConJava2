package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.Helper.TipoMoneda;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Cuentas")
@Getter @Setter
public class Cuenta {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer numerooCuenta;
  private Float saldo;
  @Enumerated(EnumType.STRING)
  private TipoMoneda tipoMoneda;
  private String tipoCuenta;



}
