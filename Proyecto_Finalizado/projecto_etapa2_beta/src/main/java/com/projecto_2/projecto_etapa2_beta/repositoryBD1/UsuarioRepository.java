package com.projecto_2.projecto_etapa2_beta.repositoryBD1;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);
  

    
}
