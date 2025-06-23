package com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;
import com.projecto_2.projecto_etapa2_beta.repositoryBD2.PedidoRepository;


@Service
public class PedidoServiceImpl implements PedidoService {

    
    @Autowired
    private PedidoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findByAll() {
        return (List<Pedido>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    @Transactional
    public Optional<Pedido> delete(Pedido pedido) {
        Optional<Pedido> optional = repository.findById(pedido.getId_pedido());
        optional.ifPresent(repository::delete);
        return optional;
    }
}
