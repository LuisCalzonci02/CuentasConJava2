package com.NttData.CuentasSevice.entity;

import com.NttData.CuentasSevice.Helper.TipoMoneda;
import lombok.AllArgsConstructor;
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

  private Integer idcliente;

  private Integer numeroCuenta;
  private Float saldo;
  @Enumerated(EnumType.STRING)
  private TipoMoneda tipoMoneda;
  private String tipoCuenta;
  private float acuerdo;

  public Cuenta(Integer idcliente, Integer numeroCuenta, Float saldo, TipoMoneda tipoMoneda, String tipoCuenta, float acuerdo) {
    this.idcliente = idcliente;
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldo;
    this.tipoMoneda = tipoMoneda;
    this.tipoCuenta = tipoCuenta;
    this.acuerdo = acuerdo;
  }

  public Cuenta() {
  }
}
