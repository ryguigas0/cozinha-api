package com.espm.guilherme.cozinhaapi.pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status", columnDefinition = "integer default 1")
    private int status = 1; // CANCELADO = 0, AGUARDANDO = 1, PREPARANDO = 2, PRONTO = 3, SERVIDO = 4

    @Column(name = "cliente_id")
    private int clienteId;

    public PedidoModel() {
    }

    public PedidoModel(PedidoRequestTO novoPedido) {
        this.clienteId = novoPedido.clienteId();
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public int getClienteId() {
        return clienteId;
    }

}
