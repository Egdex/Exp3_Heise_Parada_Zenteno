package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Catalogo;



public interface CatalogoService {

    List<Catalogo> findByAll();

    Optional<Catalogo> findById(Long id);

    Catalogo save(Catalogo unProducto);

    Optional<Catalogo> delete(Catalogo unProducto);
    

}
