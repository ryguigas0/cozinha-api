package com.espm.guilherme.cozinhaapi.pedido;

import java.util.List;

public record PedidoResponseTO(int id, double total, int clienteId, String status, int statusNumber,
        List<ItemMenuReferenciaResponseTO> items) {
    public PedidoResponseTO(int id, double total, int clienteId, int status, List<ItemMenuReferenciaResponseTO> items) {
        this(id, total, clienteId, parseStatus(status), status, items);
    }

    public PedidoResponseTO(int id, int clienteId, int status,
            List<ItemMenuReferenciaResponseTO> items) {

        this(id, calcularTotal(items), clienteId, parseStatus(status), status, items);
    }

    private static Double calcularTotal(List<ItemMenuReferenciaResponseTO> items) {

        double total = 0.0;

        for (ItemMenuReferenciaResponseTO itemMenuReferenciaResponseTO : items) {
            total += itemMenuReferenciaResponseTO.preco();
        }

        return total;
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
            case 5:
                parsedStatus = "FECHADO";
                break;

            default:
                throw new RuntimeException("PEDIDO STATUS DESCONHECIDO");
        }

        return parsedStatus;
    }
}
