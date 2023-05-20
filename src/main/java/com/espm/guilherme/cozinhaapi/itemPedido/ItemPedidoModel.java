package com.espm.guilherme.cozinhaapi.itemPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_item_menu")
public class ItemPedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pedido_id")
    private int pedidoId;

    @Column(name = "item_menu_id")
    private int itemMenuId;

    public ItemPedidoModel() {
    }

    public ItemPedidoModel(ItemPedidoRequestTO to) {
        this.pedidoId = to.pedidoId();
        this.itemMenuId = to.itemMenuId();
    }

    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public int getItemMenuId() {
        return itemMenuId;
    }

}
