package com.espm.guilherme.cozinhaapi.itemMenuPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMenuPedidoService {

    @Autowired
    ItemMenuPedidoRepository repo;

    public void criar(ItemMenuPedidoRequestTO newItemPedido) {
        repo.save(new ItemMenuPedidoModel(newItemPedido));
    }

    public List<ItemMenuPedidoResponseTO> listarItemMenuPorPedidoID(int pedidoId) {
        return repo.listarPorPedidoID(pedidoId).stream().map(m -> new ItemMenuPedidoResponseTO(m.getId(), m.getPedidoId(), m.getItemMenuId())).toList();
    }

}
