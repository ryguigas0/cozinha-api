package com.espm.guilherme.cozinhaapi.pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repo;

    public PedidoResponseTO criarPedido() {
        PedidoModel model = repo.save(new PedidoModel());

        return new PedidoResponseTO(model.getId(), model.getStatus());
    }

    public List<PedidoResponseTO> listarPedidos(int situacao) {
        List<PedidoResponseTO> output = new ArrayList<>();

        if (situacao == 0) {
            repo.findAll().forEach(p -> output.add(new PedidoResponseTO(p.getId(), p.getStatus())));
        } else {
            repo.listarPorStatus(situacao).forEach(p -> output.add(new PedidoResponseTO(p.getId(), p.getStatus())));
        }

        return output;
    }

    public PedidoResponseTO atualizarPedido(int pedidoId, int situacaoNova) {
        int i = repo.atualizarPedido(pedidoId, situacaoNova);

        System.out.println("Update result " + i);

        PedidoModel model = repo.findById(pedidoId).get();

        return new PedidoResponseTO(model.getId(), model.getStatus());
    }

}