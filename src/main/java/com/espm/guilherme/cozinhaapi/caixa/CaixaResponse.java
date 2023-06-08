package com.espm.guilherme.cozinhaapi.caixa;

public record CaixaResponse(String message, int resultCode) {
    public CaixaResponse(int resultCode) {
        this(parseResultNumber(resultCode), resultCode);
    }

    private static String parseResultNumber(int resultNumber) {
        switch (resultNumber) {
            case 0:
                return "OPERAÇÃO COM SUCESSO";
            case -1:
                return "PEDIDO INVÁLIDO";
            default:
                return "CÓDIGO '" + resultNumber + "' DESCONHECIDO";
        }
    }
}
