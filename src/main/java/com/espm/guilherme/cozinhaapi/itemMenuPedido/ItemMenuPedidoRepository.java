package com.espm.guilherme.cozinhaapi.itemMenuPedido;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuPedidoRepository extends CrudRepository<ItemMenuPedidoModel, Integer> {

    @Query(value = "select pim.* from pedido_item_menu pim where pim.pedido_id = :id", nativeQuery = true)
    List<ItemMenuPedidoModel> listarPorPedidoID(@Param("id") int pedidoId);

}
