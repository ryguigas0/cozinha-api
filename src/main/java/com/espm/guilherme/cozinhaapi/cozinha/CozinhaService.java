package com.espm.guilherme.cozinhaapi.cozinha;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.ingrediente.IngredienteService;
import com.espm.guilherme.cozinhaapi.pedido.ItemMenuIngredienteReferenciaResponseTO;
import com.espm.guilherme.cozinhaapi.pedido.ItemMenuReferenciaResponseTO;
import com.espm.guilherme.cozinhaapi.pedido.PedidoResponseTO;
import com.espm.guilherme.cozinhaapi.pedido.PedidoService;

@Service
public class CozinhaService {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    IngredienteService ingredienteService;

    public List<PedidoResponseTO> listarPedidos(int situacao) {
        if (situacao >= 0 && situacao <= 4) {
            return pedidoService.listarPedidos(situacao);
        } else {
            return pedidoService.listarPedidos();
        }
    }

    public CozinhaResponse prepararPedido(int pedidoId) {
        PedidoResponseTO pedido = pedidoService.buscarPorPedidoId(pedidoId);

        if (pedido.statusNumber() != 1) {
            return new CozinhaResponse(-2);
        }

        pedidoService.atualizarPedido(pedidoId, 2);

        Map<Integer, Integer> ingredienteIdQuantidade = checarQuantidadeIngredientes(pedido);

        if (ingredienteIdQuantidade == null) {
            return new CozinhaResponse(-1);
        }

        for (Map.Entry<Integer, Integer> entry : ingredienteIdQuantidade.entrySet()) {
            int rows = ingredienteService.reduzirQuantidade(entry.getKey(), entry.getValue());
            if (rows == 0) {
                throw new RuntimeException("QUANTIDADE DE INGREDIENTE NÃO FOI ATUALIZADA");
            }
        }

        pedidoService.atualizarPedido(pedidoId, 3);

        return new CozinhaResponse(0);
    }

    private TreeMap<Integer, Integer> checarQuantidadeIngredientes(PedidoResponseTO pedido) {
        TreeMap<Integer, Integer> ingredienteIdQuantidade = new TreeMap<>();

        for (ItemMenuReferenciaResponseTO itemMenuPedido : pedido.items()) {
            for (ItemMenuIngredienteReferenciaResponseTO ingrediente : itemMenuPedido.ingredientes()) {
                int novaQuantidadeTotal = ingrediente.quantidadeNecessaria();

                if (ingredienteIdQuantidade.containsKey(ingrediente.id())) {
                    novaQuantidadeTotal += ingredienteIdQuantidade.get(ingrediente.id());
                }

                ingredienteIdQuantidade.put(ingrediente.id(), novaQuantidadeTotal);
            }
        }

        for (Map.Entry<Integer, Integer> ingredienteQuantidade : ingredienteIdQuantidade.entrySet()) {
            if (!ingredienteService.quantidadeSuficiente(ingredienteQuantidade.getKey(),
                    ingredienteQuantidade.getValue())) {
                return null;
            }
        }

        return ingredienteIdQuantidade;
    }

}
