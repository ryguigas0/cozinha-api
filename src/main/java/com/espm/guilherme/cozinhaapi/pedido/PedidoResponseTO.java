package com.espm.guilherme.cozinhaapi.pedido;

import java.util.List;

public record PedidoResponseTO(int id, String status, List<ItemMenuReferenciaResponseTO> items) {
    public PedidoResponseTO(int id, int status, List<ItemMenuReferenciaResponseTO> items) {
        this(id, parseStatus(status), items);
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
