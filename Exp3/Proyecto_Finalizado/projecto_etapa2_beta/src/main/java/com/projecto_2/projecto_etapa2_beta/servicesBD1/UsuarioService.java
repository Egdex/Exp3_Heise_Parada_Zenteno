package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;

public interface UsuarioService {

    List<Usuario> findByAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario unUsuario);

    Optional<Usuario> delete(Usuario unUsuario);

    
    
}
