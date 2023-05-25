package com.espm.guilherme.cozinhaapi.cozinha;

public record CozinhaResponse(String message, int resultCode) {

    public CozinhaResponse(int resultCode) {
        this(parseResultNumber(resultCode), resultCode);
    }

    private static String parseResultNumber(int resultNumber) {
        switch (resultNumber) {
            case 0:
                return "OPERAÇÃO COM SUCESSO";
            case -1:
                return "QUANTIDADE DE INGREDIENTES INSUFICIENTE";
            case -2:
                return "PEDIDO INVÁLIDO";
            default:
                return "CÓDIGO '" + resultNumber + "' DESCONHECIDO";
        }
    }
}
