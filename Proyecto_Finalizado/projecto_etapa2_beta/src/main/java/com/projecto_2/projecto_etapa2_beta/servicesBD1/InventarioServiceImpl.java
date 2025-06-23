package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Inventario;
import com.projecto_2.projecto_etapa2_beta.repositoryBD1.InventarioRepository;

@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    private InventarioRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Inventario> findByAll() {

        return (List<Inventario>) repository.findAll();
    }


    @Override
    public Optional<Inventario> delete(Inventario unProducto) {
        Optional<Inventario> productoOptional = repository.findById(unProducto.getId_producto());
        productoOptional.ifPresent(Inventario -> {
            repository.delete(unProducto);
        });
        return productoOptional;
    }

    
    @Override
    @Transactional(readOnly = true)
    public Optional<Inventario> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Inventario save(Inventario unProducto) {
        return repository.save(unProducto);
    }

}
