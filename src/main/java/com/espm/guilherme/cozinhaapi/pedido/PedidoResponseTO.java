package com.espm.guilherme.cozinhaapi.pedido;

public record PedidoResponseTO(int id, String status) {
    public PedidoResponseTO(int id, int status) {
        this(id, parseStatus(status));

    }

    private static String parseStatus(int status) {
        String parsedStatus = "";

        switch (status) {
            case 1:
                parsedStatus = "AGUARDANDO";
                break;
            case 2:
                parsedStatus = "PREPARANDO";
                break;
            case 3:
                parsedStatus = "PRONTO";
                break;
            case 4:
                parsedStatus = "SERVIDO";
                break;

            default:
                throw new RuntimeException("PEDIDO STATUS DESCONHECIDO");
        }

        return parsedStatus;
    }
}
