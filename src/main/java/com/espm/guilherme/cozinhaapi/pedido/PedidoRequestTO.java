package com.espm.guilherme.cozinhaapi.pedido;

public record PedidoRequestTO(int clienteId, ItemMenuReferenciaRequestTO[] items) {
    
}
