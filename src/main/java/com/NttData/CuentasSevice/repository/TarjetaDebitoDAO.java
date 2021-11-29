package com.NttData.CuentasSevice.repository;


import com.NttData.CuentasSevice.entity.TarjetaDebito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarjetaDebitoDAO extends CrudRepository <TarjetaDebito, Integer>{
//    List<TarjetaDebito> findByName(@Param("name") Integer id);
}
