package com.projecto_2.projecto_etapa2_beta.repository;

import org.springframework.data.repository.CrudRepository;

import com.projecto_2.projecto_etapa2_beta.entities.Catalogo;

public interface CatalogoRepository extends CrudRepository<Catalogo, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, puedes definir métodos para buscar productos por nombre, categoría, etc.
    // public List<Catalogo> findByNombre(String nombre);

}
