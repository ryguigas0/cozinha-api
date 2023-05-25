package com.espm.guilherme.cozinhaapi.pedido;

import java.util.List;

public record PedidoResponseTO(int id, String status, int statusNumber, List<ItemMenuReferenciaResponseTO> items) {
    public PedidoResponseTO(int id, int status, List<ItemMenuReferenciaResponseTO> items) {
        this(id, parseStatus(status), status, items);
    }

    private static String parseStatus(int status) {
        String parsedStatus = "";

        switch (status) {
            case 0:
                parsedStatus = "CANCELADO";
                break;
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
