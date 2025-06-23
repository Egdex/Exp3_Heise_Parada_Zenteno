package com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;
import com.projecto_2.projecto_etapa2_beta.repositoryBD2.CarritoRepository;


@Service
public class CarritoServiceImpl implements CarritoService {


    @Autowired
    private CarritoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> findByAll() {
        return (List<Carrito>) repository.findAll();
    }

    @Override
    public Optional<Carrito> findById(Long id_carrito) {
    return repository.findById(id_carrito);
    }


    @Override
    @Transactional
    public Carrito save(Carrito carrito) {
        return repository.save(carrito);
    }

    @Override
    public Optional<Carrito> delete(Carrito carrito) {
        Optional<Carrito> carritoOptional = repository.findById(carrito.getId_carrito());
        carritoOptional.ifPresent(repository::delete);
        return carritoOptional;
    }
}
