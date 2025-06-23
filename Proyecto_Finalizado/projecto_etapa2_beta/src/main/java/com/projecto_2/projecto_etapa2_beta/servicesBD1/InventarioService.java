package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Inventario;

public interface InventarioService {

    List<Inventario> findByAll();

    Optional<Inventario> findById(Long id);

    Inventario save(Inventario unProducto);

    Optional<Inventario> delete(Inventario unProducto);
}
