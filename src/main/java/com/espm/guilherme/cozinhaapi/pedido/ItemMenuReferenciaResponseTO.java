package com.espm.guilherme.cozinhaapi.pedido;

import java.util.List;

public record ItemMenuReferenciaResponseTO(String nome, double preco, List<ItemMenuIngredienteReferenciaResponseTO> ingredientes) {
    
}
