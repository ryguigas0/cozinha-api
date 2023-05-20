package com.espm.guilherme.cozinhaapi.itemPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository repo;

    public ItemPedidoResponseTO criar(ItemPedidoRequestTO newItemPedido) {

        ItemPedidoModel model = repo.save(new ItemPedidoModel(newItemPedido));

        return new ItemPedidoResponseTO(model.getId(), model.getPedidoId(), model.getItemMenuId());
    }

}
