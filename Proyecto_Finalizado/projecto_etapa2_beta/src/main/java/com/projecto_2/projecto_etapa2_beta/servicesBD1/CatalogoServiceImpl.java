package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Catalogo;
import com.projecto_2.projecto_etapa2_beta.repositoryBD1.CatalogoRepository;


@Service
public class CatalogoServiceImpl implements CatalogoService {
    @Autowired
    private CatalogoRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Catalogo> findByAll() {
        return (List<Catalogo>) repository.findAll();
    }


    @Override
    public Optional<Catalogo> delete(Catalogo unProducto) {
        Optional<Catalogo> productoOptional = repository.findById(unProducto.getId());
        productoOptional.ifPresent(producto -> {
            repository.delete(unProducto);
        });  
        return productoOptional;
    }

    
    @Override
    @Transactional(readOnly = true)
    public Optional<Catalogo> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Catalogo save(Catalogo unProducto) {
        return repository.save(unProducto);
    }

}
