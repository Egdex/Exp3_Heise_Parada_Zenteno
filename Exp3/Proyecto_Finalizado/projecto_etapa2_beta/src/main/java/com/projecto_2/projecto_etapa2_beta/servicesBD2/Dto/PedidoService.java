package com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto;



import java.util.List;
import java.util.Optional;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;

public interface PedidoService {
    List<Pedido> findByAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    Optional<Pedido> delete(Pedido pedido);
}
