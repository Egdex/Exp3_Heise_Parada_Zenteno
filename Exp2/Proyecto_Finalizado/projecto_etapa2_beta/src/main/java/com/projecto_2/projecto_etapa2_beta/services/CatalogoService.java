package com.projecto_2.projecto_etapa2_beta.services;

import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entities.Catalogo;



public interface CatalogoService {

    List<Catalogo> findByAll();

    Optional<Catalogo> findById(Long id);

    Catalogo save(Catalogo unProducto);

    Optional<Catalogo> delete(Catalogo unProducto);
    

}
