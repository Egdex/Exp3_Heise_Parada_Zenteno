package com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto;


import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;

public interface CarritoService {
    List<Carrito> findByAll();
    Optional<Carrito> findById(Long id_carrito);
    Carrito save(Carrito carrito);
    Optional<Carrito> delete(Carrito carrito);
}