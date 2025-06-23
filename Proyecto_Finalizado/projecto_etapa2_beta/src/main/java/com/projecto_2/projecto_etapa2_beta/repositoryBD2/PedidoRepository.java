package com.projecto_2.projecto_etapa2_beta.repositoryBD2;

import org.springframework.data.repository.CrudRepository;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar pedidos por estado o usuario

}
