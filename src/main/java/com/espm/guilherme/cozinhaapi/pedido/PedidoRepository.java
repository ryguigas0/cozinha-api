package com.espm.guilherme.cozinhaapi.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

@Transactional
public interface PedidoRepository extends CrudRepository<PedidoModel, Integer>{

    @Query( value = "select p.* from pedido p where p.status = :situacao order by p.data_criacao desc", nativeQuery = true)
    List<PedidoModel> listarPorStatus(int situacao);

    @Modifying()
    @Query(value = "update pedido set status = :situacao where id = :id", nativeQuery = true)
    int atualizarPedido(@Param("id") int pedidoId, @Param("situacao") int situacao);

}
