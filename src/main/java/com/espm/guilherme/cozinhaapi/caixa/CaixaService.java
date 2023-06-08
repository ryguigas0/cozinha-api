package com.espm.guilherme.cozinhaapi.caixa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.pedido.PedidoResponseTO;
import com.espm.guilherme.cozinhaapi.pedido.PedidoService;

@Service
public class CaixaService {

    @Autowired
    PedidoService pedidoService;

    public List<PedidoResponseTO> listarPedidosFechar() {
        return pedidoService.listarPedidos(4);
    }

    public CaixaResponse fecharPedido(int pedidoId) {
        PedidoResponseTO pedido = pedidoService.buscarPorPedidoId(pedidoId);

        if (pedido.statusNumber() != 4) {
            return new CaixaResponse(-1);
        }

        pedidoService.atualizarPedido(pedidoId, 5);

        return new CaixaResponse(0);
    }

}
