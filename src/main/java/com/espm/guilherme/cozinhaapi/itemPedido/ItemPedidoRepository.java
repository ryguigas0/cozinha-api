package com.espm.guilherme.cozinhaapi.itemPedido;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedidoModel, Integer> {

}
