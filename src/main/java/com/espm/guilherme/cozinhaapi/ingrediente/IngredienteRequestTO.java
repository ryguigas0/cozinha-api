package com.espm.guilherme.cozinhaapi.ingrediente;

public record IngredienteRequestTO(
        String nome,
        int quantidade,
        String unidade) {
}
