package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;
import com.projecto_2.projecto_etapa2_beta.repositoryBD1.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() {
        return (List<Usuario>) repository.findAll();
    }


    @Override
    public Optional<Usuario> delete(Usuario unUsuario) {
        Optional<Usuario> productoOptional = repository.findById(unUsuario.getId());
        productoOptional.ifPresent(Usuario -> {
            repository.delete(unUsuario);});  
        return productoOptional;
    }

    
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Usuario save(Usuario unUsuario) {
        return repository.save(unUsuario);
    }

}
