package com.espm.guilherme.cozinhaapi.itemMenuPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMenuPedidoService {

    @Autowired
    ItemMenuPedidoRepository repo;

    public ItemMenuPedidoResponseTO criar(ItemMenuPedidoRequestTO newItemPedido) {
        ItemMenuPedidoModel model = repo.save(new ItemMenuPedidoModel(newItemPedido));

        return new ItemMenuPedidoResponseTO(model.getId(),  model.getPedidoId(), model.getItemMenuId());
    }

    public List<ItemMenuPedidoResponseTO> listarItemMenuPorPedidoID(int pedidoId) {
        return repo.listarPorPedidoID(pedidoId).stream().map(m -> new ItemMenuPedidoResponseTO(m.getId(), m.getPedidoId(), m.getItemMenuId())).toList();
    }

}
