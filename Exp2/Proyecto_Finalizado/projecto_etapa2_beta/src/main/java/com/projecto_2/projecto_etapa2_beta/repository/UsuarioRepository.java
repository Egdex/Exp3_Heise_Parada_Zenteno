package com.projecto_2.projecto_etapa2_beta.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projecto_2.projecto_etapa2_beta.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);
  

    
}
